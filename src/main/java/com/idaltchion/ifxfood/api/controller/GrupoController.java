package com.idaltchion.ifxfood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idaltchion.ifxfood.api.assembler.GrupoDTOAssembler;
import com.idaltchion.ifxfood.domain.repository.GrupoRepository;

@RestController
@RequestMapping(path = "/grupos")
public class GrupoController {	
	
	@Autowired
	GrupoRepository grupoRepository;

	@Autowired
	GrupoDTOAssembler grupoDTOAssembler;
	
	@GetMapping
	public List<?> listar() {
		return grupoDTOAssembler.toCollectionDTO(grupoRepository.findAll());
	}
	
}
