package com.idaltchion.ifxfood.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.idaltchion.ifxfood.api.openapi.controller.FluxoPedidoControllerOpenAPI;
import com.idaltchion.ifxfood.domain.service.FluxoPedidoService;

@RestController
@RequestMapping(path = "/pedidos/{codigo_pedido}", produces = MediaType.APPLICATION_JSON_VALUE)
public class FluxoPedidoController implements FluxoPedidoControllerOpenAPI {

	@Autowired
	FluxoPedidoService fluxoPedidoService;
	
	@PutMapping("/confirmacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> confirmacao(@PathVariable String codigo_pedido) {
		fluxoPedidoService.confirmacao(codigo_pedido);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/entrega")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> entrega(@PathVariable String codigo_pedido) {
		fluxoPedidoService.entrega(codigo_pedido);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/cancelamento")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> cancelamento(@PathVariable String codigo_pedido) {
		fluxoPedidoService.cancelamento(codigo_pedido);
		return ResponseEntity.noContent().build();
	}
	
}
