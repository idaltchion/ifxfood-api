package com.idaltchion.ifxfood.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

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
	public CollectionModel<FormaPagamentoDTO> listar(
			@ApiParam(value = "Codigo do restaurante", example = "3", required = true)
			Long restauranteId);
	
	@ApiOperation("Associa uma forma de pagamento ao restaurante")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Associação realizada com sucesso"),
		@ApiResponse(responseCode = "404", description = "Restaurante ou forma de pagamento não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<Void> associar(
			@ApiParam(value = "Codigo da forma de pagamento", example = "5", required = true)
			Long formaPagamentoId, 
			@ApiParam(value = "Codigo do restaurante", example = "3", required = true)
			Long restauranteId);
	
	@ApiOperation(value = "Desassocia uma forma de pagamento do restaurante")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Desassociação realizada com sucesso"),
		@ApiResponse(responseCode = "404", description = "Restaurante ou forma de pagamento não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<Void> desassociar(
			@ApiParam(value = "Codigo da forma de pagamento", example = "5", required = true)
			Long formaPagamentoId, 
			@ApiParam(value = "Codigo do restaurante", example = "3", required = true)
			Long restauranteId);
}
