package com.idaltchion.ifxfood.api.v2.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
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

import com.idaltchion.ifxfood.api.ResourceUriHelper;
import com.idaltchion.ifxfood.api.v2.assembler.CidadeDTOAssemberV2;
import com.idaltchion.ifxfood.api.v2.assembler.CidadeDTODisassemblerV2;
import com.idaltchion.ifxfood.api.v2.model.CidadeDTOV2;
import com.idaltchion.ifxfood.api.v2.model.input.CidadeDTOInputV2;
import com.idaltchion.ifxfood.core.web.IfxMediaTypes;
import com.idaltchion.ifxfood.domain.exception.EstadoNaoEncontradoException;
import com.idaltchion.ifxfood.domain.exception.NegocioException;
import com.idaltchion.ifxfood.domain.model.Cidade;
import com.idaltchion.ifxfood.domain.repository.CidadeRepository;
import com.idaltchion.ifxfood.domain.service.CadastroCidadeService;

@RestController
@RequestMapping(path = "/cidades", produces = IfxMediaTypes.V2_APPLICATION_JSON_VALUE)
public class CidadeControllerV2 {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private CadastroCidadeService cadastroCidadeService;
	
	@Autowired
	private CidadeDTOAssemberV2 cidadeDTOAssembler;
	
	@Autowired
	private CidadeDTODisassemblerV2 cidadeDTODisassembler;
	
	@GetMapping
	public CollectionModel<CidadeDTOV2> listar() {
		return cidadeDTOAssembler.toCollectionModel(cidadeRepository.findAll());
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public CidadeDTOV2 buscar(@PathVariable Long id) {
		CidadeDTOV2 cidadeDTO = cidadeDTOAssembler.toModelWithCollectionRel(cadastroCidadeService.buscar(id));		
		return cidadeDTO;
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		cadastroCidadeService.remover(id);
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public CidadeDTOV2 adicionar(@RequestBody @Valid CidadeDTOInputV2 cidadeDTOInput) {
		try {
			Cidade cidade = cidadeDTODisassembler.toDomainObject(cidadeDTOInput);
			CidadeDTOV2 cidadeDTO = cidadeDTOAssembler.toModel(cadastroCidadeService.salvar(cidade));
			
			ResourceUriHelper.addUriInResponse(cidadeDTO.getIdCidade());
			
			return cidadeDTO;
		}
		catch(EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public CidadeDTOV2 atualizar(@PathVariable Long id, @RequestBody @Valid CidadeDTOInputV2 cidadeDTOInput) {
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
