package com.idaltchion.ifxfood.domain.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.idaltchion.ifxfood.domain.event.PedidoCanceladoEvent;
import com.idaltchion.ifxfood.domain.model.Pedido;
import com.idaltchion.ifxfood.domain.service.EnvioEmailService;
import com.idaltchion.ifxfood.domain.service.EnvioEmailService.Mensagem;

@Component
public class NotificacaoClientePedidoCanceladoListener {

	@Autowired
	private EnvioEmailService envioEmail;
	
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void aoCancelarPedido(PedidoCanceladoEvent event) {
		Pedido pedido = event.getPedido();
		
		var mensagem = Mensagem.builder()
				.assunto(pedido.getRestaurante().getNome() + " - Pedido cancelado")
				.corpo("pedido-cancelado.html")
				.variavel("pedido", pedido)
				.destinatario(pedido.getCliente().getEmail())
				.build();

		envioEmail.enviar(mensagem);
	}
	
}
