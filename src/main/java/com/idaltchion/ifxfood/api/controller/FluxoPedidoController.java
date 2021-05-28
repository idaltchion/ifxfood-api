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
@RequestMapping("/pedidos/{codigo_pedido}")
public class FluxoPedidoController {

	@Autowired
	FluxoPedidoService fluxoPedidoService;
	
	@PutMapping("/confirmacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void confirmacao(@PathVariable String codigo_pedido) {
		fluxoPedidoService.confirmacao(codigo_pedido);
	}
	
	@PutMapping("/entrega")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void entrega(@PathVariable String codigo_pedido) {
		fluxoPedidoService.entrega(codigo_pedido);
	}
	
	@PutMapping("/cancelamento")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void cancelamento(@PathVariable String codigo_pedido) {
		fluxoPedidoService.cancelamento(codigo_pedido);
	}
	
}
