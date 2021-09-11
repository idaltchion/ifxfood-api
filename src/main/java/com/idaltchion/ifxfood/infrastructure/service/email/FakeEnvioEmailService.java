package com.idaltchion.ifxfood.infrastructure.service.email;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FakeEnvioEmailService extends SmtpEnvioEmailService {

	@Override
	public void enviar(Mensagem mensagem) {
		String corpo = processarTemplate(mensagem);
		log.info("[FAKE-EMAIL] Envio de email realizado com sucesso:");
		log.info("Destinatario: {}", mensagem.getDestinatarios());
		log.info("Assunto: {}", mensagem.getAssunto());
		log.info("Corpo: {}", corpo);
	}
	
}
