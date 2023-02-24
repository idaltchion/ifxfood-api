package com.idaltchion.ifxfood.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.idaltchion.ifxfood.api.ResourceUriHelper;
import com.idaltchion.ifxfood.api.assembler.CidadeDTOAssember;
import com.idaltchion.ifxfood.api.assembler.CidadeDTODisassembler;
import com.idaltchion.ifxfood.api.model.CidadeDTO;
import com.idaltchion.ifxfood.api.model.input.CidadeDTOInput;
import com.idaltchion.ifxfood.api.openapi.controller.CidadeControllerOpenAPI;
import com.idaltchion.ifxfood.domain.exception.EstadoNaoEncontradoException;
import com.idaltchion.ifxfood.domain.exception.NegocioException;
import com.idaltchion.ifxfood.domain.model.Cidade;
import com.idaltchion.ifxfood.domain.repository.CidadeRepository;
import com.idaltchion.ifxfood.domain.service.CadastroCidadeService;

@RestController
@RequestMapping(path = "/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
public class CidadeController implements CidadeControllerOpenAPI {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private CadastroCidadeService cadastroCidadeService;
	
	@Autowired
	private CidadeDTOAssember cidadeDTOAssembler;
	
	@Autowired
	private CidadeDTODisassembler cidadeDTODisassembler;
	
	@GetMapping
	public CollectionModel<CidadeDTO> listar() {		 
		return cidadeDTOAssembler.toCollectionModel(cidadeRepository.findAll());
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public CidadeDTO buscar(@PathVariable Long id) {
		CidadeDTO cidadeDTO = cidadeDTOAssembler.toModel(cadastroCidadeService.buscar(id));		
		return cidadeDTO;
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		cadastroCidadeService.remover(id);
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public CidadeDTO adicionar(@RequestBody @Valid CidadeDTOInput cidadeDTOInput) {
		try {
			Cidade cidade = cidadeDTODisassembler.toDomainObject(cidadeDTOInput);
			CidadeDTO cidadeDTO = cidadeDTOAssembler.toModel(cadastroCidadeService.salvar(cidade));
			
			ResourceUriHelper.addUriInResponse(cidadeDTO.getId());
			
			return cidadeDTO;
		}
		catch(EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public CidadeDTO atualizar(@PathVariable Long id, @RequestBody @Valid CidadeDTOInput cidadeDTOInput) {
		try {
			Cidade cidadeAtual = cadastroCidadeService.buscar(id);
			cidadeDTODisassembler.copyToDomainObject(cidadeDTOInput, cidadeAtual);
			return cidadeDTOAssembler.toModel(cadastroCidadeService.salvar(cidadeAtual));
		}
		catch(EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e.getCause());
		}
	}

}
