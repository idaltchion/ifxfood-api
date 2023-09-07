package com.idaltchion.ifxfood.api.v2.controller;

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

import com.idaltchion.ifxfood.api.v2.assembler.EstadoDTOAssemblerV2;
import com.idaltchion.ifxfood.api.v2.assembler.EstadoDTODisassemblerV2;
import com.idaltchion.ifxfood.api.v2.model.EstadoDTOV2;
import com.idaltchion.ifxfood.api.v2.model.input.EstadoDTOInputV2;
import com.idaltchion.ifxfood.api.v2.openapi.controller.EstadoControllerOpenAPIV2;
import com.idaltchion.ifxfood.domain.model.Estado;
import com.idaltchion.ifxfood.domain.repository.EstadoRepository;
import com.idaltchion.ifxfood.domain.service.CadastroEstadoService;

@RestController
@RequestMapping(path = "/v2/estados", produces = MediaType.APPLICATION_JSON_VALUE)
public class EstadoControllerV2 implements EstadoControllerOpenAPIV2 {

	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private EstadoDTOAssemblerV2 estadoAssembler;
	
	@Autowired
	private EstadoDTODisassemblerV2 estadoDisassembler;
	
	@Autowired
	private CadastroEstadoService cadastroEstadoService;
	
	@GetMapping
	public CollectionModel<EstadoDTOV2> listar() {
		return estadoAssembler.toCollectionModel(estadoRepository.findAll());
	}
	
	@GetMapping("/{codigo}")
	public EstadoDTOV2 buscar(@PathVariable Long codigo) {
		return estadoAssembler.toModelWithCollectionRel(cadastroEstadoService.buscar(codigo));
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public EstadoDTOV2 adicionar(@RequestBody @Valid EstadoDTOInputV2 estadoInput) {
		Estado estado = estadoDisassembler.toDomainObject(estadoInput);
		return estadoAssembler.toModel(cadastroEstadoService.salvar(estado));
	}
	
	@PutMapping("/{codigo}")
	public EstadoDTOV2 atualizar(@PathVariable Long codigo, @RequestBody @Valid EstadoDTOInputV2 estadoInput) {
		Estado estadoAtual = cadastroEstadoService.buscar(codigo);
		estadoDisassembler.copyToDomainObject(estadoInput, estadoAtual);
		return estadoAssembler.toModel(cadastroEstadoService.salvar(estadoAtual));
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		cadastroEstadoService.remover(codigo);
	}
	
}
