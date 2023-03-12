package com.idaltchion.ifxfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.idaltchion.ifxfood.api.IfxLinks;
import com.idaltchion.ifxfood.api.controller.RestauranteController;
import com.idaltchion.ifxfood.api.model.RestauranteDTO;
import com.idaltchion.ifxfood.domain.model.Restaurante;

@Component
public class RestauranteDTOAssembler extends RepresentationModelAssemblerSupport<Restaurante, RestauranteDTO> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private IfxLinks ifxLinks;
	
	public RestauranteDTOAssembler() {
		super(RestauranteController.class, RestauranteDTO.class);
	}
	
	/*
	 * efetua a conversao do objeto de Modelo de Dominio para objeto de Modelo de Representacao (DTO de Saida)
	 */
	@Override
	public RestauranteDTO toModel(Restaurante restaurante) {
		
		RestauranteDTO restauranteDTO = createModelWithId(restaurante.getId(), restaurante);
		modelMapper.map(restaurante, restauranteDTO);
		restauranteDTO.getCozinha().add(ifxLinks.linkToCozinha(restauranteDTO.getCozinha().getId()));
		if (restauranteDTO.getEndereco() != null) {
			restauranteDTO.getEndereco().getCidade().add(ifxLinks.linkToCidade(restauranteDTO.getEndereco().getCidade().getId()));
		}
		
		return restauranteDTO;
	}
	
	@Override
	public CollectionModel<RestauranteDTO> toCollectionModel(Iterable<? extends Restaurante> restaurantes) {
		return super.toCollectionModel(restaurantes).add(WebMvcLinkBuilder.linkTo(RestauranteController.class).withSelfRel());
	}
	
}
