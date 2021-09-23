package com.idaltchion.ifxfood.api.controller.openapi;

import java.util.List;

import com.idaltchion.ifxfood.api.exceptionhandler.Problem;
import com.idaltchion.ifxfood.api.model.CidadeDTO;
import com.idaltchion.ifxfood.api.model.input.CidadeDTOInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Cidade")
public interface CidadeControllerOpenAPI {
	
	@ApiOperation("Lista todas as cidades cadastradas")
	public List<CidadeDTO> listar();
	
	@ApiResponses({
		@ApiResponse(responseCode = "400", description = "Resource id is invalid", content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Resource not found", content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	@ApiOperation("Busca uma cidade pelo id")
	public CidadeDTO buscar(
			@ApiParam(value = "Codigo da cidade", example = "9")
			Long id);
	
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Resource deleted"),
		@ApiResponse(responseCode = "400", description = "Resource id is invalid", content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Resource not found", content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	@ApiOperation("Remove uma cidade pelo id")
	public void remover(
			@ApiParam(value = "Codigo da cidade")
			Long id);
	
	@ApiOperation("Cadastra uma nova cidade")
	public CidadeDTO adicionar(CidadeDTOInput cidadeDTOInput);
	
	@ApiResponses({
		@ApiResponse(responseCode = "400", description = "Resource id is invalid", content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Resource not found", content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	@ApiOperation("Atualiza uma cidade pelo id")
	public CidadeDTO atualizar(
			@ApiParam(value = "Codigo da cidade")
			Long id, 
			CidadeDTOInput cidadeDTOInput);

}
