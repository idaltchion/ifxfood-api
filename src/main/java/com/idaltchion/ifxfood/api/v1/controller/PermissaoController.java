package com.idaltchion.ifxfood.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idaltchion.ifxfood.api.v1.assembler.PermissaoDTOAssembler;
import com.idaltchion.ifxfood.api.v1.model.PermissaoDTO;
import com.idaltchion.ifxfood.domain.repository.PermissaoRepository;

@RestController
@RequestMapping(path = "/v1/permissoes")
public class PermissaoController {
	
	@Autowired
	private PermissaoRepository permissaoRepository;

	@Autowired
	private PermissaoDTOAssembler permissaoDTOAssembler;
	
	@GetMapping
	public CollectionModel<PermissaoDTO> listar() {
		return permissaoDTOAssembler.toCollectionModel(permissaoRepository.findAll());
	}
	
}
