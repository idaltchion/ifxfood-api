package com.idaltchion.ifxfood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.idaltchion.ifxfood.domain.model.Pedido;
import com.idaltchion.ifxfood.domain.service.EnvioEmailService.Mensagem;

@Service
public class FluxoPedidoService {

	@Autowired
	CadastroPedidoService pedidoService;
	
	@Autowired
	private EnvioEmailService envioEmail;
	
	@Transactional
	public void confirmacao(String codigo_pedido) {
		Pedido pedido = pedidoService.buscar(codigo_pedido);
		pedido.confirmar();
		
		var mensagem = Mensagem.builder()
				.assunto(pedido.getRestaurante().getNome() + " - Pedido confirmado")
				.corpo("Pedido de código <strong>" + pedido.getCodigo() + "</strong> confirmado!")
				.destinatario(pedido.getCliente().getEmail())
				.build();
		
		envioEmail.enviar(mensagem );
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
	}
	
}
