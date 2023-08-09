package com.idaltchion.ifxfood.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.idaltchion.ifxfood.api.v1.IfxLinks;
import com.idaltchion.ifxfood.api.v1.assembler.FormaPagamentoDTOAssembler;
import com.idaltchion.ifxfood.api.v1.model.FormaPagamentoDTO;
import com.idaltchion.ifxfood.api.v1.openapi.controller.RestauranteFormaPagamentoControllerOpenAPI;
import com.idaltchion.ifxfood.domain.model.FormaPagamento;
import com.idaltchion.ifxfood.domain.model.Restaurante;
import com.idaltchion.ifxfood.domain.service.CadastroFormaPagamentoService;
import com.idaltchion.ifxfood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping(path = "/restaurantes/{restauranteId}/formas-pagamento", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteFormaPagamentoController implements RestauranteFormaPagamentoControllerOpenAPI {

	@Autowired
	CadastroRestauranteService restauranteService;
	
	@Autowired
	FormaPagamentoDTOAssembler formaPagamentoAssembler;
	
	@Autowired
	CadastroFormaPagamentoService formaPagamentoService;
	
	@Autowired
	private IfxLinks ifxLinks;
	
	@GetMapping
	public CollectionModel<FormaPagamentoDTO> listar(@PathVariable Long restauranteId) {
		Restaurante restauranteExistente = restauranteService.buscar(restauranteId);
		return formaPagamentoAssembler.toCollectionModelWithDesassociarFormaPagamento(restauranteExistente.getFormasPagamento(), restauranteExistente.getId())
				.removeLinks()
				.add(ifxLinks.linkToFormasPagamentoRestaurante(restauranteId, IanaLinkRelations.SELF_VALUE))
				.add(ifxLinks.linktoAssociarFormaPagamentoRestaurante(restauranteId, "associar"));
	}
	
	@PutMapping("/{formaPagamentoId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> associar(@PathVariable Long formaPagamentoId, @PathVariable Long restauranteId) {
		Restaurante restauranteExistente = restauranteService.buscar(restauranteId);
		FormaPagamento formaPagamento = formaPagamentoService.buscar(formaPagamentoId);
		restauranteService.associar(restauranteExistente, formaPagamento);
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{formaPagamentoId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> desassociar(@PathVariable Long formaPagamentoId, @PathVariable Long restauranteId) {
		Restaurante restauranteExistente = restauranteService.buscar(restauranteId);
		FormaPagamento formaPagamento = formaPagamentoService.buscar(formaPagamentoId);
		restauranteService.desassociar(restauranteExistente, formaPagamento);
		return ResponseEntity.noContent().build();
	}
	
}
