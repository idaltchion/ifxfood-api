package com.idaltchion.ifxfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.idaltchion.ifxfood.api.IfxLinks;
import com.idaltchion.ifxfood.api.controller.RestauranteProdutoController;
import com.idaltchion.ifxfood.api.model.ProdutoDTO;
import com.idaltchion.ifxfood.domain.model.Produto;

@Component
public class ProdutoDTOAssembler extends RepresentationModelAssemblerSupport<Produto, ProdutoDTO> {

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	private IfxLinks ifxLinks;
	
	public ProdutoDTOAssembler() {
		super(RestauranteProdutoController.class, ProdutoDTO.class);
	}

	@Override
	public ProdutoDTO toModel(Produto produto) {
		Long restauranteId = produto.getRestaurante().getId();
		Long produtoId = produto.getId();
		ProdutoDTO produtoDTO = createModelWithId(produtoId, produto, restauranteId);
		modelMapper.map(produto, produtoDTO);
		produtoDTO.add(ifxLinks.linkToFotoProduto(restauranteId, produtoId, "foto"));
		return produtoDTO;
	}
	
	public ProdutoDTO toModelWithCollectionRel(Produto produto) {
		ProdutoDTO produtoDTO = toModel(produto);
		produtoDTO.add(ifxLinks.linkToProdutos(produto.getRestaurante().getId(), IanaLinkRelations.COLLECTION_VALUE));
		return produtoDTO;
	}
	
	
}
