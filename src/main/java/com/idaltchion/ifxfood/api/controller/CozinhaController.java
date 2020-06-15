package com.idaltchion.ifxfood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idaltchion.ifxfood.api.domain.model.Cozinha;
import com.idaltchion.ifxfood.api.domain.repository.CozinhaRepository;
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
	
	@GetMapping
	public List<Cozinha> listar() {
		return cozinhaRepository.listar();
	}
	
	@GetMapping("/{codigo}")
	public Cozinha buscar(@PathVariable Long codigo) {
		return cozinhaRepository.buscar(codigo);
	}
	
}
