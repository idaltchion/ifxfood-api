package com.idaltchion.ifxfood.api.v2.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.idaltchion.ifxfood.api.v2.model.EstadoDTOV2;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Estados")
public interface EstadoControllerOpenAPIV2 {

	@ApiOperation("Lista todos os Estados cadastrados")
	CollectionModel<EstadoDTOV2> listar();
	
}
