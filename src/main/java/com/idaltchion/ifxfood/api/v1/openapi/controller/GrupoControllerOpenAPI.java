package com.idaltchion.ifxfood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.idaltchion.ifxfood.api.exceptionhandler.Problem;
import com.idaltchion.ifxfood.api.v1.model.GrupoDTO;
import com.idaltchion.ifxfood.api.v1.model.input.GrupoDTOInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Grupo")
public interface GrupoControllerOpenAPI {	
	
	@ApiOperation("Lista todos os grupos cadastrados")
	CollectionModel<GrupoDTO> listar();
	
	@ApiOperation("Busca um grupo pelo id")
	@ApiResponses({
		@ApiResponse(responseCode = "400", description = "Resource id is invalid", content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Resource not found", content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	GrupoDTO buscar(
			@ApiParam(value = "Codigo do grupo", example = "5")
			Long id);
	
	@ApiOperation("Cadastra um novo grupo")
	GrupoDTO adicionar(GrupoDTOInput grupoInput);
	
	@ApiOperation("Remove um grupo pelo id")
	@ApiResponses({
		@ApiResponse(responseCode = "400", description = "Resource id is invalid", content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Resource not found", content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	void remover(
			@ApiParam(value = "Codigo do grupo", example = "5")
			Long id);
	
	@ApiOperation("Atualiza um grupo pelo id")
	@ApiResponses({
		@ApiResponse(responseCode = "400", description = "Resource id is invalid", content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Resource not found", content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	GrupoDTO atualizar(
			@ApiParam(value = "Codigo do grupo", example = "5")
			Long id, 
			GrupoDTOInput grupoInput);
	
}
