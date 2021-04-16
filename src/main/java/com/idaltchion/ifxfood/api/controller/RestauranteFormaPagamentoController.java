package com.idaltchion.ifxfood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.idaltchion.ifxfood.api.assembler.FormaPagamentoDTOAssembler;
import com.idaltchion.ifxfood.api.model.FormaPagamentoDTO;
import com.idaltchion.ifxfood.domain.model.FormaPagamento;
import com.idaltchion.ifxfood.domain.model.Restaurante;
import com.idaltchion.ifxfood.domain.service.CadastroFormaPagamentoService;
import com.idaltchion.ifxfood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/formas-pagamento")
public class RestauranteFormaPagamentoController {

	@Autowired
	CadastroRestauranteService restauranteService;
	
	@Autowired
	FormaPagamentoDTOAssembler formaPagamentoAssembler;
	
	@Autowired
	CadastroFormaPagamentoService formaPagamentoService;
	
	@GetMapping
	public List<FormaPagamentoDTO> listar(@PathVariable Long restauranteId) {
		Restaurante restauranteExistente = restauranteService.buscar(restauranteId);
		return formaPagamentoAssembler.toDTOCollection(restauranteExistente.getFormasPagamento());
	}
	
	@PutMapping("/{formaPagamentoId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void associar(@PathVariable Long formaPagamentoId, @PathVariable Long restauranteId) {
		Restaurante restauranteExistente = restauranteService.buscar(restauranteId);
		FormaPagamento formaPagamento = formaPagamentoService.buscar(formaPagamentoId);
		restauranteService.associar(restauranteExistente, formaPagamento);
	}
	
	@DeleteMapping("/{formaPagamentoId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void desassociar(@PathVariable Long formaPagamentoId, @PathVariable Long restauranteId) {
		Restaurante restauranteExistente = restauranteService.buscar(restauranteId);
		FormaPagamento formaPagamento = formaPagamentoService.buscar(formaPagamentoId);
		restauranteService.desassociar(restauranteExistente, formaPagamento);
	}
	
}
