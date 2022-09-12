package com.idaltchion.ifxfood.api.openapi.controller;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.idaltchion.ifxfood.api.exceptionhandler.Problem;
import com.idaltchion.ifxfood.api.model.CozinhaDTO;
import com.idaltchion.ifxfood.api.model.input.CozinhaDTOInput;
import com.idaltchion.ifxfood.domain.model.Cozinha;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Cozinha")
public interface CozinhaControllerOpenAPI {
	
	@ApiOperation(value = "Lista todas as cozinhas cadastradas")
	Page<CozinhaDTO> listar(Pageable pageable);
	
	@ApiResponses({
		@ApiResponse(responseCode = "400", description = "Resource id is invalid", content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Resource not found", content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	@ApiOperation(value = "Busca uma cozinha existente pelo id")
	CozinhaDTO buscar(
			@ApiParam(value = "Codigo da cozinha", example = "3")
			Long id);
	
	@ApiOperation(value = "Cadastra uma nova cozinha")
	@ApiResponse(responseCode = "201", description = "Resource created")
	CozinhaDTO adicionar(CozinhaDTOInput cozinhaInput);
	
	@ApiResponses({
		@ApiResponse(responseCode = "400", description = "Resource id is invalid", content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Resource not found", content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	@ApiOperation(value = "Atualiza uma cozinha")
	CozinhaDTO atualizar(
			@ApiParam(value = "Codigo da cozinha", example = "2")
			Long id, 
			CozinhaDTOInput cozinhaInput);
	
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Resource deleted"),
		@ApiResponse(responseCode = "400", description = "Resource id is invalid", content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Resource not found", content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	@ApiOperation(value = "Remove uma cozinha")
	void remover(
			@ApiParam(value = "Codigo da cozinha", example = "3")
			Long id);
	
	@ApiOperation(value = "Busca a primeira cozinha existente")
	Optional<Cozinha> buscarPrimeira();

}
