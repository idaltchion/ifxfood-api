package com.idaltchion.ifxfood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.idaltchion.ifxfood.api.assembler.CidadeDTOAssember;
import com.idaltchion.ifxfood.api.assembler.CidadeDTODisassembler;
import com.idaltchion.ifxfood.api.exceptionhandler.Problem;
import com.idaltchion.ifxfood.api.model.CidadeDTO;
import com.idaltchion.ifxfood.api.model.input.CidadeDTOInput;
import com.idaltchion.ifxfood.domain.exception.EstadoNaoEncontradoException;
import com.idaltchion.ifxfood.domain.exception.NegocioException;
import com.idaltchion.ifxfood.domain.model.Cidade;
import com.idaltchion.ifxfood.domain.repository.CidadeRepository;
import com.idaltchion.ifxfood.domain.service.CadastroCidadeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Cidade")
@RestController
@RequestMapping("/cidades")
public class CidadeController {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private CadastroCidadeService cadastroCidadeService;
	
	@Autowired
	private CidadeDTOAssember cidadeDTOAssembler;
	
	@Autowired
	private CidadeDTODisassembler cidadeDTODisassembler;
	
	@ApiOperation("Lista todas as cidades cadastradas")
	@GetMapping
	public List<CidadeDTO> listar() {
		return cidadeDTOAssembler.toDTOCollection(cidadeRepository.findAll());
	}
	
	@ApiResponses({
		@ApiResponse(responseCode = "400", description = "Resource id is invalid", content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Resource not found", content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	@ApiOperation("Busca uma cidade pelo id")
	@GetMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public CidadeDTO buscar(
			@ApiParam(value = "Codigo da cidade", example = "9")
			@PathVariable Long id) {
		return cidadeDTOAssembler.toDTO(cadastroCidadeService.buscar(id));
	}
	
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Resource deleted"),
		@ApiResponse(responseCode = "400", description = "Resource id is invalid", content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Resource not found", content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	@ApiOperation("Remove uma cidade pelo id")
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void remover(
			@ApiParam(value = "Codigo da cidade")
			@PathVariable Long id) {
		cadastroCidadeService.remover(id);
	}
	
	@ApiOperation("Cadastra uma nova cidade")
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public CidadeDTO adicionar(@RequestBody @Valid CidadeDTOInput cidadeDTOInput) {
		try {
			Cidade cidade = cidadeDTODisassembler.toDomainObject(cidadeDTOInput);
			return cidadeDTOAssembler.toDTO(cadastroCidadeService.salvar(cidade));
		}
		catch(EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	@ApiResponses({
		@ApiResponse(responseCode = "400", description = "Resource id is invalid", content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Resource not found", content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	@ApiOperation("Atualiza uma cidade pelo id")
	@PutMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public CidadeDTO atualizar(
			@ApiParam(value = "Codigo da cidade")
			@PathVariable Long id, 
			
			@RequestBody @Valid CidadeDTOInput cidadeDTOInput) {
		try {
			Cidade cidadeAtual = cadastroCidadeService.buscar(id);
			cidadeDTODisassembler.copyToDomainObject(cidadeDTOInput, cidadeAtual);
			return cidadeDTOAssembler.toDTO(cadastroCidadeService.salvar(cidadeAtual));
		}
		catch(EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e.getCause());
		}
	}

}
