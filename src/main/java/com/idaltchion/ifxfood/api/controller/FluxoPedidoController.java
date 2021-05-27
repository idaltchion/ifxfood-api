package com.idaltchion.ifxfood.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.idaltchion.ifxfood.domain.service.FluxoPedidoService;

@RestController
@RequestMapping("/pedidos/{pedido_id}")
public class FluxoPedidoController {

	@Autowired
	FluxoPedidoService fluxoPedidoService;
	
	@PutMapping("/confirmacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void confirmacao(@PathVariable Long pedido_id) {
		fluxoPedidoService.confirmacao(pedido_id);
	}
	
	@PutMapping("/entrega")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void entrega(@PathVariable Long pedido_id) {
		fluxoPedidoService.entrega(pedido_id);
	}
	
	@PutMapping("/cancelamento")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void cancelamento(@PathVariable Long pedido_id) {
		fluxoPedidoService.cancelamento(pedido_id);
	}
	
}
