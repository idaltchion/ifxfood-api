package com.idaltchion.ifxfood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.idaltchion.ifxfood.api.v1.IfxLinks;
import com.idaltchion.ifxfood.api.v1.controller.CozinhaController;
import com.idaltchion.ifxfood.api.v1.model.CozinhaDTO;
import com.idaltchion.ifxfood.domain.model.Cozinha;

@Component
public class CozinhaDTOAssembler extends RepresentationModelAssemblerSupport<Cozinha, CozinhaDTO>{

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private IfxLinks ifxLinks;
	
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
		cozinhaDTO.add(ifxLinks.linkToCozinhas("cozinhas"));
		
		return cozinhaDTO;
	}
	
}
