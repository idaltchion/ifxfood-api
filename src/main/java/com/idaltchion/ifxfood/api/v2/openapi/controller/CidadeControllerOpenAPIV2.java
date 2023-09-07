package com.idaltchion.ifxfood.api.v2.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;

import com.idaltchion.ifxfood.api.v2.model.CidadeDTOV2;
import com.idaltchion.ifxfood.core.web.IfxMediaTypes;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Cidades")
public interface CidadeControllerOpenAPIV2 {

	@ApiOperation(value = "Lista todas as cidades cadastradas", produces = MediaType.APPLICATION_JSON_VALUE + "," + IfxMediaTypes.V2_APPLICATION_JSON_VALUE)
	public CollectionModel<CidadeDTOV2> listar();

	
}
