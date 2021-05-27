package com.idaltchion.ifxfood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.idaltchion.ifxfood.domain.model.Pedido;

@Service
public class FluxoPedidoService {

	@Autowired
	CadastroPedidoService pedidoService;
	
	@Transactional
	public void confirmacao(Long pedido_id) {
		Pedido pedido = pedidoService.buscar(pedido_id);
		pedido.confirmar();
	}

	@Transactional
	public void entrega(Long pedido_id) {
		Pedido pedido = pedidoService.buscar(pedido_id);
		pedido.entregar();
	}

	@Transactional
	public void cancelamento(Long pedido_id) {
		Pedido pedido = pedidoService.buscar(pedido_id);
		pedido.cancelar();
	}
	
}
