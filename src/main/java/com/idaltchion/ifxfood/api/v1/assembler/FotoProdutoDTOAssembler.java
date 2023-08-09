package com.idaltchion.ifxfood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.idaltchion.ifxfood.api.v1.IfxLinks;
import com.idaltchion.ifxfood.api.v1.controller.RestauranteProdutoController;
import com.idaltchion.ifxfood.api.v1.model.FotoProdutoDTO;
import com.idaltchion.ifxfood.domain.model.FotoProduto;

@Component
public class FotoProdutoDTOAssembler extends RepresentationModelAssemblerSupport<FotoProduto, FotoProdutoDTO> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private IfxLinks ifxLinks;
	
	public FotoProdutoDTOAssembler() {
		super(RestauranteProdutoController.class, FotoProdutoDTO.class);
	}
	
	@Override
	public FotoProdutoDTO toModel(FotoProduto foto) {
		FotoProdutoDTO fotoProdutoDTO = modelMapper.map(foto, FotoProdutoDTO.class);
		Long restauranteId = foto.getProduto().getId();
		Long produtoId = foto.getProdutoId();
		fotoProdutoDTO.add(ifxLinks.linkToFotoProduto(restauranteId, produtoId, IanaLinkRelations.SELF_VALUE));
		fotoProdutoDTO.add(ifxLinks.linkToProduto(restauranteId, produtoId, "produto"));
		
		return fotoProdutoDTO;
	}

}
