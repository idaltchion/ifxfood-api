package com.idaltchion.ifxfood.api.openapi.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.idaltchion.ifxfood.api.exceptionhandler.Problem;
import com.idaltchion.ifxfood.api.model.RestauranteApenasNomeDTO;
import com.idaltchion.ifxfood.api.model.RestauranteDTO;
import com.idaltchion.ifxfood.api.model.RestauranteTaxaFreteDTO;
import com.idaltchion.ifxfood.api.model.input.RestauranteDTOInput;
import com.idaltchion.ifxfood.domain.model.Restaurante;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Restaurante")
public interface RestauranteControllerOpenAPI {

	@ApiOperation(value = "Lista os restaurantes cadastrados")
	@ApiImplicitParams({
			@ApiImplicitParam(value = "Nome da projeção de pedidos. Ex: apenas-nome", allowableValues = "apenas-nome",
					name = "projecao", paramType = "query", type = "string")
	})
	CollectionModel<RestauranteTaxaFreteDTO> listar();
	
	@ApiOperation(value = "Lista restaurantes", hidden = true)
	CollectionModel<RestauranteApenasNomeDTO> listarApenasNomes();
	
	@ApiResponses({
		@ApiResponse(responseCode = "400", description = "ID do restaurante inválido", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Problem.class)))),
		@ApiResponse(responseCode = "404", description = "Restaurante não localizado", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Problem.class))))
	})
	@ApiOperation(value = "Busca um restaurante pelo id")
	RestauranteDTO buscar(
			@ApiParam(value = "Código do restaurante", example = "2")
			Long id);
	
	@ApiResponses({
		@ApiResponse(responseCode = "201", description = "Restaurante cadastrado")
	})
	@ApiOperation(value = "Cadastra um novo restaurante")
	RestauranteDTO adicionar(
			@ApiParam(name = "corpo", value = "Representação de um novo restaurante cadastrado")
			RestauranteDTOInput restauranteInput);
	
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Restaurante atualizado"),
		@ApiResponse(responseCode = "404", description = "Restaurante não localizado", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Problem.class))))
	})
	@ApiOperation(value = "Atualiza um restaurante pelo id")
	RestauranteDTO atualizar(
			@ApiParam(value = "Código do restaurante", example = "2")
			Long id, 
			RestauranteDTOInput restauranteInput);
	
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Restaurante atualizado"),
		@ApiResponse(responseCode = "404", description = "Restaurante não localizado", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Problem.class))))
	})
	@ApiOperation(value = "Atualiza parcialmente um restaurante pelo id")
	RestauranteDTO atualizarParcial(
			@ApiParam(value = "Código do restaurante", example = "2")
			Long id, 
			Map<String, Object> campos, 
			HttpServletRequest request);
	
	@ApiOperation(value = "Lista restaurantes cadastrados que possuem frete grátis")
	CollectionModel<RestauranteDTO> restauranteComFreteGratis(
			@ApiParam(value = "Nome do restaurante")
			String nome);
	
	@ApiOperation(value = "Lista o primeiro restaurante cadastrado")
	Optional<Restaurante> buscarPrimeiro();
	
	@ApiOperation(value = "Ativa um restaurante pelo id")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Restaurante ativado"),
		@ApiResponse(responseCode = "400", description = "ID do restaurante inválido", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Problem.class)))),
		@ApiResponse(responseCode = "404", description = "Restaurante não localizado", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Problem.class))))
	})
	ResponseEntity<Void> ativar(
			@ApiParam(value = "Código do restaurante", example = "2")
			Long id);
	
	@ApiOperation(value = "Ativa múltiplos restaurantes pelo id")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Restaurantes ativados")
	})
	void ativarMultiplos(
			@ApiParam(value = "Códigos dos restaurantes", example = "1, 3, 5")
			List<Long> restauranteIds);
	
	@ApiOperation(value = "Inativa um restaurante pelo id")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Restaurante inativado"),
		@ApiResponse(responseCode = "400", description = "ID do restaurante inválido", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Problem.class)))),
		@ApiResponse(responseCode = "404", description = "Restaurante não localizado", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Problem.class))))
	})
	ResponseEntity<Void> inativar(
			@ApiParam(value = "Código do restaurante", example = "2")
			Long id);
	
	@ApiOperation(value = "Inativa múltiplos restaurantes pelo id")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Restaurantes inativados")
	})
	void inativarMultiplos(
			@ApiParam(value = "Códigos dos restaurantes", example = "1, 3, 5")
			List<Long> restauranteIds);
	
	@ApiOperation(value = "Realiza a abertura de um restaurante pelo id")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Restaurante aberto"),
		@ApiResponse(responseCode = "400", description = "ID do restaurante inválido", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Problem.class)))),
		@ApiResponse(responseCode = "404", description = "Restaurante não localizado", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Problem.class))))
	})
	ResponseEntity<Void> abrir(
			@ApiParam(value = "Código do restaurante", example = "2")
			Long id);
	
	
	@ApiOperation(value = "Realiza o fechamento de um restaurente pelo id")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Restaurante fechado"),
		@ApiResponse(responseCode = "400", description = "ID do restaurante inválido", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Problem.class)))),
		@ApiResponse(responseCode = "404", description = "Restaurante não localizado", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Problem.class))))
	})
	ResponseEntity<Void> fechar(
			@ApiParam(value = "Código do restaurante", example = "2")
			Long id);
	
}
