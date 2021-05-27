package com.idaltchion.ifxfood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.idaltchion.ifxfood.domain.exception.NegocioException;
import com.idaltchion.ifxfood.domain.model.Pedido;
import com.idaltchion.ifxfood.domain.model.StatusPedido;

@Service
public class FluxoPedidoService {

	private static final String MSG_PEDIDO_NAO_ALTERADO = "Status do pedido não pode ser '%s' pois esta atualmente com status '%s'";
	
	@Autowired
	CadastroPedidoService pedidoService;
	
	@Transactional
	public void confirmacao(Long pedido_id) {
		Pedido pedido = pedidoService.buscar(pedido_id);
		if (pedido.getStatus().getDescricao() != StatusPedido.CRIADO.getDescricao()) {
			throw new NegocioException(String.format(MSG_PEDIDO_NAO_ALTERADO, 
					StatusPedido.CONFIRMADO.getDescricao(), pedido.getStatus().getDescricao()));
		}
		pedido.setStatus(StatusPedido.CONFIRMADO);
	}

	@Transactional
	public void entrega(Long pedido_id) {
		Pedido pedido = pedidoService.buscar(pedido_id);
		if (pedido.getStatus().getDescricao() != StatusPedido.CONFIRMADO.getDescricao()) {
			throw new NegocioException(String.format(MSG_PEDIDO_NAO_ALTERADO, 
					StatusPedido.ENTREGUE.getDescricao(), pedido.getStatus().getDescricao()));
		}
		pedido.setStatus(StatusPedido.ENTREGUE);
	}

	@Transactional
	public void cancelamento(Long pedido_id) {
		Pedido pedido = pedidoService.buscar(pedido_id);
		if (pedido.getStatus().getDescricao() != StatusPedido.CRIADO.getDescricao()) {
			throw new NegocioException(String.format(MSG_PEDIDO_NAO_ALTERADO, 
					StatusPedido.CANCELADO.getDescricao(), pedido.getStatus().getDescricao()));
		}
		pedido.setStatus(StatusPedido.CANCELADO);
	}

	
}
