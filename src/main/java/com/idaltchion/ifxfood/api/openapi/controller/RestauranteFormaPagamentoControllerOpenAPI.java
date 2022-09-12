package com.idaltchion.ifxfood.api.openapi.controller;

import java.util.List;

import com.idaltchion.ifxfood.api.exceptionhandler.Problem;
import com.idaltchion.ifxfood.api.model.FormaPagamentoDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Restaurante")
public interface RestauranteFormaPagamentoControllerOpenAPI {
	
	@ApiOperation("Lista as formas de pagamento associadas ao restaurante")
	@ApiResponses({
		@ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public List<FormaPagamentoDTO> listar(
			@ApiParam(value = "Codigo do restaurante", example = "3", required = true)
			Long restauranteId);
	
	@ApiOperation("Associa uma forma de pagamento ao restaurante")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Associação realizada com sucesso"),
		@ApiResponse(responseCode = "404", description = "Restaurante ou forma de pagamento não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public void associar(
			@ApiParam(value = "Codigo da forma de pagamento", example = "5", required = true)
			Long formaPagamentoId, 
			@ApiParam(value = "Codigo do restaurante", example = "3", required = true)
			Long restauranteId);
	
	@ApiOperation(value = "Desassocia uma forma de pagamento do restaurante")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Desassociação realizada com sucesso"),
		@ApiResponse(responseCode = "404", description = "Restaurante ou forma de pagamento não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public void desassociar(
			@ApiParam(value = "Codigo da forma de pagamento", example = "5", required = true)
			Long formaPagamentoId, 
			@ApiParam(value = "Codigo do restaurante", example = "3", required = true)
			Long restauranteId);
}
