package com.idaltchion.ifxfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.idaltchion.ifxfood.api.IfxLinks;
import com.idaltchion.ifxfood.api.controller.RestauranteController;
import com.idaltchion.ifxfood.api.model.RestauranteTaxaFreteDTO;
import com.idaltchion.ifxfood.domain.model.Restaurante;

@Component
public class RestauranteTaxaFreteDTOAssembler extends RepresentationModelAssemblerSupport<Restaurante, RestauranteTaxaFreteDTO> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private IfxLinks ifxLinks;
	
	public RestauranteTaxaFreteDTOAssembler() {
		super(RestauranteController.class, RestauranteTaxaFreteDTO.class);
	}
	
	@Override
	public RestauranteTaxaFreteDTO toModel(Restaurante restaurante) {
		RestauranteTaxaFreteDTO restauranteTaxaFreteDTO = createModelWithId(restaurante.getId(), restaurante);
		modelMapper.map(restaurante, restauranteTaxaFreteDTO);
		restauranteTaxaFreteDTO.getCozinha().add(ifxLinks.linkToCozinha(restauranteTaxaFreteDTO.getCozinha().getId()));
		return restauranteTaxaFreteDTO;
	}
	
	@Override
	public CollectionModel<RestauranteTaxaFreteDTO> toCollectionModel(Iterable<? extends Restaurante> restaurantes) {
		return super.toCollectionModel(restaurantes).add(ifxLinks.linkToRestaurantes(IanaLinkRelations.SELF_VALUE));
	}
	
}
