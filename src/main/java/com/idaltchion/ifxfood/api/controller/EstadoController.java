package com.idaltchion.ifxfood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idaltchion.ifxfood.api.domain.exception.EntidadeEmUsoException;
import com.idaltchion.ifxfood.api.domain.exception.EntidadeNaoEncontradaException;
import com.idaltchion.ifxfood.api.domain.model.Estado;
import com.idaltchion.ifxfood.api.domain.repository.EstadoRepository;
import com.idaltchion.ifxfood.api.domain.service.CadastroEstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController {

	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CadastroEstadoService cadastroEstadoService;
	
	@GetMapping
	public List<Estado> listar() {
		return estadoRepository.findAll();
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Estado> buscar(@PathVariable Long codigo) {
		Optional<Estado> estado = estadoRepository.findById(codigo); 
		if (estado.isPresent()) {
			return ResponseEntity.ok(estado.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping
	public ResponseEntity<Estado> adicionar(@RequestBody Estado estado) {
		estado = cadastroEstadoService.salvar(estado);
		return ResponseEntity.status(HttpStatus.CREATED).body(estado);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Estado> atualizar(@PathVariable Long codigo, @RequestBody Estado estado) {
		//TODO: manter os metodos atualizar padranizados. vide Cidade
		Estado estadoAtual = estadoRepository.findById(codigo).orElse(null);
		if (estadoAtual != null) {
			BeanUtils.copyProperties(estado, estadoAtual, "id");
			estadoAtual = cadastroEstadoService.salvar(estadoAtual);
			return ResponseEntity.ok(estadoAtual);
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{codigo}")
	public ResponseEntity<?> remover(@PathVariable Long codigo) {
		try {
			cadastroEstadoService.remover(codigo);
			return ResponseEntity.noContent().build();
		}
		catch(EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
		catch(EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}
	
}