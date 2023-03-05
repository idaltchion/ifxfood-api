package com.idaltchion.ifxfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.idaltchion.ifxfood.api.controller.CozinhaController;
import com.idaltchion.ifxfood.api.model.CozinhaDTO;
import com.idaltchion.ifxfood.domain.model.Cozinha;

@Component
public class CozinhaDTOAssembler extends RepresentationModelAssemblerSupport<Cozinha, CozinhaDTO>{

	@Autowired
	private ModelMapper modelMapper;
	
	public CozinhaDTOAssembler() {
		super(CozinhaController.class, CozinhaDTO.class);
	}
	
	@Override
	public CozinhaDTO toModel(Cozinha cozinha) {
		CozinhaDTO cozinhaDTO = createModelWithId(cozinha.getId(), cozinha);
		modelMapper.map(cozinha, cozinhaDTO);
		
		return cozinhaDTO;
	}
	
	public CozinhaDTO toModelWithCollectionRel(Cozinha cozinha) {
		CozinhaDTO cozinhaDTO = toModel(cozinha);
		cozinhaDTO.add(WebMvcLinkBuilder.linkTo(CozinhaController.class).withRel(IanaLinkRelations.COLLECTION));
		
		return cozinhaDTO;
	}
	
}
