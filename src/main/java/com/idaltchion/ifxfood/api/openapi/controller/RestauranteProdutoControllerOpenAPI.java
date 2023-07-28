package com.idaltchion.ifxfood.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.idaltchion.ifxfood.api.model.ProdutoDTO;

import io.swagger.annotations.Api;

/*
 * TODO: Ajustar documentacao do swagger
 */
@Api(tags = "Restaurante")
public interface RestauranteProdutoControllerOpenAPI {

	public CollectionModel<ProdutoDTO> listar(
			Long restauranteId, 
			Boolean exibirInativos
	);
	
}
