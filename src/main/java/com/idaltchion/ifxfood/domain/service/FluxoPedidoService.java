package com.idaltchion.ifxfood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.idaltchion.ifxfood.domain.model.Pedido;
import com.idaltchion.ifxfood.domain.repository.PedidoRepository;

@Service
public class FluxoPedidoService {

	@Autowired
	CadastroPedidoService pedidoService;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Transactional
	public void confirmacao(String codigo_pedido) {
		Pedido pedido = pedidoService.buscar(codigo_pedido);
		pedido.confirmar();
		pedidoRepository.save(pedido);
	}

	@Transactional
	public void entrega(String codigo_pedido) {
		Pedido pedido = pedidoService.buscar(codigo_pedido);
		pedido.entregar();
	}

	@Transactional
	public void cancelamento(String codigo_pedido) {
		Pedido pedido = pedidoService.buscar(codigo_pedido);
		pedido.cancelar();
		pedidoRepository.save(pedido);
	}
	
}
