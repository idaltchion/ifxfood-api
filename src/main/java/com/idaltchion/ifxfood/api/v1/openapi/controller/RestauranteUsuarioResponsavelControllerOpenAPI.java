package com.idaltchion.ifxfood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.idaltchion.ifxfood.api.exceptionhandler.Problem;
import com.idaltchion.ifxfood.api.v1.model.UsuarioDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/*
 * TODO: Ajustar documentacao do swagger
 */
@Api(tags = "Restaurante")
public interface RestauranteUsuarioResponsavelControllerOpenAPI {

	@ApiOperation(value = "Lista os responsáveis associados ao restaurante")
	@ApiResponses({
		@ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public CollectionModel<UsuarioDTO> listar(
			@ApiParam(value = "Codigo do restaurante", example = "5", required = true)
			Long restaurante_id);
	
	@ApiOperation(value = "Associa um responsável ao restaurante")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Responsavel associado com sucesso"),
		@ApiResponse(responseCode = "404", description = "Responsavel ou restaurante nao encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<Void> associar(
			@ApiParam(value = "Codigo do restaurante", example = "5", required = true)
			Long restaurante_id, 
			@ApiParam(value = "Codigo do usuario responsavel", example = "3", required = true)
			Long usuario_id);
	
	@ApiOperation(value = "Desassocia um responsável do restaurante")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Responsavel desassociado com sucesso"),
		@ApiResponse(responseCode = "404", description = "Responsavel ou restaurante nao encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<Void> desassociar(
			@ApiParam(value = "Codigo do restaurante", example = "5", required = true)
			Long restaurante_id, 
			@ApiParam(value = "Codigo do usuario responsavel", example = "3", required = true)
			Long usuario_id);
	
}
