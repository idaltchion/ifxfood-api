package com.idaltchion.ifxfood.infrastructure.service.email;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.idaltchion.ifxfood.core.email.EmailProperties;

public class SandboxEnvioEmailService extends SmtpEnvioEmailService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private EmailProperties emailProperties;
	
	@Override
	public void enviar(Mensagem mensagem) {		
		try {
			MimeMessage mimeMessage = criarMimeMessage(mensagem);
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
			
			helper.setTo(emailProperties.getSandbox().getDestinatario());
			
			javaMailSender.send(mimeMessage);
			
		} catch (Exception e) {
			throw new EmailException("Problema ao enviar email.", e);
		}
		
		
		
		
	}

}
