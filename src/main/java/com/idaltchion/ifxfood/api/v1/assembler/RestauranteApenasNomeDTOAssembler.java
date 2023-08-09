package com.idaltchion.ifxfood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.idaltchion.ifxfood.api.v1.IfxLinks;
import com.idaltchion.ifxfood.api.v1.controller.RestauranteController;
import com.idaltchion.ifxfood.api.v1.model.RestauranteApenasNomeDTO;
import com.idaltchion.ifxfood.domain.model.Restaurante;

@Component
public class RestauranteApenasNomeDTOAssembler extends RepresentationModelAssemblerSupport<Restaurante, RestauranteApenasNomeDTO> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private IfxLinks ifxLinks;
	
	public RestauranteApenasNomeDTOAssembler() {
		super(RestauranteController.class, RestauranteApenasNomeDTO.class);
	}
	
	@Override
	public RestauranteApenasNomeDTO toModel(Restaurante restaurante) {
		RestauranteApenasNomeDTO restauranteApenasNomeDTO = createModelWithId(restaurante.getId(), restaurante);
		modelMapper.map(restaurante, restauranteApenasNomeDTO);
		
		return restauranteApenasNomeDTO;
	}
	
	@Override
	public CollectionModel<RestauranteApenasNomeDTO> toCollectionModel(Iterable<? extends Restaurante> restaurantes) {
		return super.toCollectionModel(restaurantes).add(ifxLinks.linkToRestaurantes(IanaLinkRelations.SELF_VALUE));
	}
}
