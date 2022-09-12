package com.idaltchion.ifxfood.api.openapi.controller;

import com.idaltchion.ifxfood.api.exceptionhandler.Problem;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Pedido")
public interface FluxoPedidoControllerOpenAPI {

	@ApiOperation(value = "Efetua a confirmação de um pedido")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Pedido confirmado com sucesso"),
		@ApiResponse(responseCode = "404", description = "Pedido não localizado", content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	void confirmacao(
			@ApiParam(value = "Código do pedido", example = "2049d811-cd30-4b8e-bd0b-42acc26f93e8")
			String codigo_pedido);
	
	@ApiOperation(value = "Efetua a entrega de um pedido")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Pedido entregue com sucesso"),
		@ApiResponse(responseCode = "404", description = "Pedido não localizado", content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	void entrega(
			@ApiParam(value = "Código do pedido", example = "2049d811-cd30-4b8e-bd0b-42acc26f93e8")
			String codigo_pedido);
	
	@ApiOperation(value = "Efetua o cancelamento de um pedido")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Pedido cancelado com sucesso"),
		@ApiResponse(responseCode = "404", description = "Pedido não localizado", content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	void cancelamento(
			@ApiParam(value = "Código do pedido", example = "2049d811-cd30-4b8e-bd0b-42acc26f93e8")
			String codigo_pedido);
	
}
