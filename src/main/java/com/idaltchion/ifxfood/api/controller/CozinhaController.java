package com.idaltchion.ifxfood.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.idaltchion.ifxfood.api.assembler.CozinhaDTOAssembler;
import com.idaltchion.ifxfood.api.assembler.CozinhaDTODisassembler;
import com.idaltchion.ifxfood.api.model.CozinhaDTO;
import com.idaltchion.ifxfood.api.model.input.CozinhaDTOInput;
import com.idaltchion.ifxfood.domain.model.Cozinha;
import com.idaltchion.ifxfood.domain.repository.CozinhaRepository;
import com.idaltchion.ifxfood.domain.service.CadastroCozinhaService;

/*
 * produces: suporta no Accept (header) tanto application/json quanto application/xml, o default, mesmo sem especificar, é JSON
 * entretanto, deve adicionar no pom.xml a dependencia jackson-dataformat
 */
@RestController
@RequestMapping(value = "/cozinhas", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class CozinhaController {

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Autowired
	private CadastroCozinhaService cadastroCozinhaService;
	
	@Autowired
	private CozinhaDTOAssembler cozinhaDTOAssembler;
	
	@Autowired
	private CozinhaDTODisassembler cozinhaDTODisassembler;

	@GetMapping
	public List<CozinhaDTO> listar() {
		return cozinhaDTOAssembler.toDTOCollection(cozinhaRepository.findAll());
	}

	@GetMapping("/{id}")
	public CozinhaDTO buscar(@PathVariable Long id) {
		return cozinhaDTOAssembler.toDTO(cadastroCozinhaService.buscarOuFalhar(id));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CozinhaDTO adicionar(@RequestBody @Valid CozinhaDTOInput cozinhaInput) {
		Cozinha cozinha = cozinhaDTODisassembler.toDomainObject(cozinhaInput);
		return cozinhaDTOAssembler.toDTO(cadastroCozinhaService.salvar(cozinha));
	}

	@PutMapping("/{id}")
	public CozinhaDTO atualizar(@PathVariable Long id, @RequestBody CozinhaDTOInput cozinhaInput) {
		Cozinha cozinhaAtual = cadastroCozinhaService.buscarOuFalhar(id);
		cozinhaDTODisassembler.copyToDomainObject(cozinhaInput, cozinhaAtual);
		return cozinhaDTOAssembler.toDTO(cadastroCozinhaService.salvar(cozinhaAtual));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		cadastroCozinhaService.excluir(id);
	}

	@GetMapping("/buscar-primeira")
	public Optional<Cozinha> buscarPrimeira() {
		return cozinhaRepository.buscarPrimeiroRegistro();
	}
}
