package com.idaltchion.ifxfood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.idaltchion.ifxfood.api.domain.exception.EntidadeEmUsoException;
import com.idaltchion.ifxfood.api.domain.exception.EntidadeNaoEncontradaException;
import com.idaltchion.ifxfood.api.domain.model.Cozinha;
import com.idaltchion.ifxfood.api.domain.repository.CozinhaRepository;
import com.idaltchion.ifxfood.api.domain.service.CadastroCozinhaService;
/*
 * produces: suporta no Accept (header) tanto application/json quanto application/xml, o default, mesmo sem especificar, é JSON
 * entretanto, deve adicionar no pom.xml a dependencia jackson-dataformat
 */
@RestController
@RequestMapping(
		value = "/cozinhas", 
		produces = {
				MediaType.APPLICATION_JSON_VALUE,
				MediaType.APPLICATION_XML_VALUE
		}
)
public class CozinhaController {

	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private CadastroCozinhaService cadastroCozinhaService; 
	
	@GetMapping
	public List<Cozinha> listar() {
		return cozinhaRepository.findAll();
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Cozinha> buscar(@PathVariable Long codigo) {
		Optional<Cozinha> cozinha = cozinhaRepository.findById(codigo);
		if (cozinha.isPresent()) {
//			return ResponseEntity.status(HttpStatus.OK).body(cozinha);
			return ResponseEntity.ok(cozinha.get());
		}
//		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cozinha adicionar(@RequestBody Cozinha cozinha) {
		return cadastroCozinhaService.salvar(cozinha);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cozinha> atualizar(@PathVariable Long id, @RequestBody Cozinha cozinha) {
		//TODO: refatorar os metodos atualizar para deixar padronizado, vide Cidade
		Optional<Cozinha> cozinhaAtual = cozinhaRepository.findById(id);
		if(cozinhaAtual != null) {
			BeanUtils.copyProperties(cozinha, cozinhaAtual.get(), "id");
			Cozinha cozinhaSalva = cadastroCozinhaService.salvar(cozinhaAtual.get());
			return ResponseEntity.ok(cozinhaSalva);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Cozinha> remover(@PathVariable Long id) {
		try {
			cadastroCozinhaService.excluir(id);
			return ResponseEntity.noContent().build();
		}
		catch(EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();			
		}
		catch(EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();			
		}
	}
	
	@GetMapping("/buscar-primeira")
	public Optional<Cozinha> buscarPrimeira() {
		return cozinhaRepository.buscarPrimeiroRegistro();
	}
}
