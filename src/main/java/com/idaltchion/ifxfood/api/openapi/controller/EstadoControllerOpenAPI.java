package com.idaltchion.ifxfood.api.openapi.controller;

import java.util.List;

import com.idaltchion.ifxfood.api.exceptionhandler.Problem;
import com.idaltchion.ifxfood.api.model.EstadoDTO;
import com.idaltchion.ifxfood.api.model.input.EstadoDTOInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Estado")
public interface EstadoControllerOpenAPI {
	
	@ApiOperation(value = "Lista os Estados cadastrados")
	List<EstadoDTO> listar();
	
	@ApiOperation(value = "Busca um Estado pelo código")
	@ApiResponses({
		@ApiResponse(responseCode = "400", description = "Código do Estado inválido", content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Estado não localizado", content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	EstadoDTO buscar(
			@ApiParam(value = "Código do Estado", example = "2")
			Long codigo);
	
	@ApiOperation(value = "Cadastra um novo Estado")
	@ApiResponses({
		@ApiResponse(responseCode = "201", description = "Estado cadastrado")
	})
	EstadoDTO adicionar(
			EstadoDTOInput estadoInput);
	
	@ApiOperation(value = "Atualiza um Estado pelo código")
	@ApiResponses({
		@ApiResponse(responseCode = "400", description = "Código do Estado inválido", content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Estado não localizado", content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	EstadoDTO atualizar(
			@ApiParam(value = "Código do Estado", example = "2")
			Long codigo, 
			EstadoDTOInput estadoInput);
	
	@ApiOperation(value = "Remove um Estado pelo código")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Estado removido"),
		@ApiResponse(responseCode = "400", description = "Código do Estado inválido", content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Estado não localizado", content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	void remover(
			@ApiParam(value = "Código do Estado", example = "2")
			Long codigo);

}
