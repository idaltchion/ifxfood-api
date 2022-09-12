package com.idaltchion.ifxfood.api.openapi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import com.idaltchion.ifxfood.api.model.PedidoDTO;
import com.idaltchion.ifxfood.api.model.PedidoResumoDTO;
import com.idaltchion.ifxfood.api.model.input.PedidoDTOInput;
import com.idaltchion.ifxfood.domain.filter.PedidoFilter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Pedido")
public interface PedidoControllerOpenAPI {
	
	@ApiOperation(value = "Lista todos os pedidos realizados")
	@ApiImplicitParams({
		@ApiImplicitParam(value = "Nomes das propriedades para filtrar na resposta, separadas por ponto e vírgula",
				name = "campos", paramType = "query", dataType = "string")
	})
	Page<PedidoResumoDTO> pesquisar(PedidoFilter filtro, @PageableDefault(size = 5) Pageable pageable);
	
	@ApiOperation(value = "Busca um pedido pelo codigo")
	@ApiResponses(
			@ApiResponse(responseCode = "404", description = "Pedido não localizado")
		)
	@ApiImplicitParams({
		@ApiImplicitParam(value = "Nomes das propriedades para filtrar na resposta, separadas por ponto e vírgula",
				name = "campos", paramType = "query", dataType = "string")
	})
	PedidoDTO buscar(
			@ApiParam(value = "Codigo do pedido", example = "9260898e-5e3f-48ac-8722-77df05c6f32f")
			String codigo_pedido);
	
	@ApiOperation(value = "Realiza a emissão de um pedido")
	@ApiResponses(
		@ApiResponse(responseCode = "201", description = "Pedido emitido")
	)
	PedidoDTO emitirPedido(
			@ApiParam(name = "corpo", value = "Representação de um novo pedido")
			PedidoDTOInput pedidoInput);
	
	

}
