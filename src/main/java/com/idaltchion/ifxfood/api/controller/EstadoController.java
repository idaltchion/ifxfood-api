package com.idaltchion.ifxfood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.idaltchion.ifxfood.api.assembler.EstadoDTOAssembler;
import com.idaltchion.ifxfood.api.assembler.EstadoDTODisassembler;
import com.idaltchion.ifxfood.api.model.EstadoDTO;
import com.idaltchion.ifxfood.api.model.input.EstadoDTOInput;
import com.idaltchion.ifxfood.domain.model.Estado;
import com.idaltchion.ifxfood.domain.repository.EstadoRepository;
import com.idaltchion.ifxfood.domain.service.CadastroEstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController {

	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private EstadoDTOAssembler estadoAssembler;
	
	@Autowired
	private EstadoDTODisassembler estadoDisassembler;
	
	@Autowired
	private CadastroEstadoService cadastroEstadoService;
	
	@GetMapping
	public List<EstadoDTO> listar() {
		return estadoAssembler.toCollectionDTO(estadoRepository.findAll());
	}
	
	@GetMapping("/{codigo}")
	public EstadoDTO buscar(@PathVariable Long codigo) {
		return estadoAssembler.toDTO(cadastroEstadoService.buscar(codigo));
	}
	
	@PutMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public EstadoDTO adicionar(@RequestBody @Valid EstadoDTOInput estadoInput) {
		Estado estado = estadoDisassembler.toDomainObject(estadoInput);
		return estadoAssembler.toDTO(cadastroEstadoService.salvar(estado));
	}
	
	@PutMapping("/{codigo}")
	public EstadoDTO atualizar(@PathVariable Long codigo, @RequestBody @Valid EstadoDTOInput estadoInput) {
		Estado estadoAtual = cadastroEstadoService.buscar(codigo);
		estadoDisassembler.copyToDomainObject(estadoInput, estadoAtual);
		return estadoAssembler.toDTO(cadastroEstadoService.salvar(estadoAtual));
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		cadastroEstadoService.remover(codigo);
	}
	
}
