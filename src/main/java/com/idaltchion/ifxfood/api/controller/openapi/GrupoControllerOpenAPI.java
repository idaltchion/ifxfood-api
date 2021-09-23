package com.idaltchion.ifxfood.api.controller.openapi;

import java.util.List;

import com.idaltchion.ifxfood.api.exceptionhandler.Problem;
import com.idaltchion.ifxfood.api.model.GrupoDTO;
import com.idaltchion.ifxfood.api.model.input.GrupoDTOInput;

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
	public List<GrupoDTO> listar();
	
	@ApiOperation("Busca um grupo pelo id")
	@ApiResponses({
		@ApiResponse(responseCode = "400", description = "Resource id is invalid", content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Resource not found", content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public GrupoDTO buscar(
			@ApiParam(value = "Codigo do grupo", example = "5")
			Long id);
	
	@ApiOperation("Cadastra um novo grupo")
	public GrupoDTO adicionar(GrupoDTOInput grupoInput);
	
	@ApiOperation("Remove um grupo pelo id")
	@ApiResponses({
		@ApiResponse(responseCode = "400", description = "Resource id is invalid", content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Resource not found", content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public void remover(
			@ApiParam(value = "Codigo do grupo", example = "5")
			Long id);
	
	@ApiOperation("Atualiza um grupo pelo id")
	@ApiResponses({
		@ApiResponse(responseCode = "400", description = "Resource id is invalid", content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Resource not found", content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public GrupoDTO atualizar(
			@ApiParam(value = "Codigo do grupo", example = "5")
			Long id, 
			GrupoDTOInput grupoInput);
	
}
