package com.idaltchion.ifxfood.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.idaltchion.ifxfood.api.exceptionhandler.Problem;
import com.idaltchion.ifxfood.api.model.FormaPagamentoDTO;
import com.idaltchion.ifxfood.api.model.input.FormaPagamentoDTOInput;
import com.idaltchion.ifxfood.api.openapi.model.FormaPagamentoDTOOpenAPI;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Forma de Pagamento")
public interface FormaPagamentoControllerOpenAPI {

	@ApiOperation(value = "Lista as formas de pagamentos cadastradas")
	@io.swagger.annotations.ApiResponses({ //usando "ApiResponse" v2 devido a bug na representacao do objeto utilizando "response" no swagger
		@io.swagger.annotations.ApiResponse(code = 200, message = "OK", response = FormaPagamentoDTOOpenAPI.class)
	})
	ResponseEntity<CollectionModel<FormaPagamentoDTO>> listar();
	
	@ApiOperation(value = "Busca uma forma de pagamento pelo id")
	@ApiResponses({
		@ApiResponse(responseCode = "400", description = "Resource id is invalid", content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Resource not found", content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	ResponseEntity<FormaPagamentoDTO> buscar(
			@ApiParam(value = "Codigo da forma de pagamento", example = "3")
			Long id);
	
	@ApiOperation(value = "Cadastra uma forma de pagamento")
	@ApiResponse(responseCode = "201", description = "Resource created")
	FormaPagamentoDTO adicionar(FormaPagamentoDTOInput formaPagamentoInput);
	
	@ApiOperation(value = "Remove uma forma de pagamento pelo id")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Resource deleted"),
		@ApiResponse(responseCode = "400", description = "Resource id is invalid", content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Resource not found", content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	void remover(
			@ApiParam(value = "Codigo da forma de pagamento", example = "3")
			Long id);
	
	@ApiOperation(value = "Atualiza uma forma de pagamento pelo id")
	@ApiResponses({
		@ApiResponse(responseCode = "400", description = "Resource id is invalid", content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Resource not found", content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	FormaPagamentoDTO atualizar(
			@ApiParam(value = "Codigo da forma de pagamento", example = "5")
			Long id, 
			FormaPagamentoDTOInput formaPagamentoInput);
	
}
