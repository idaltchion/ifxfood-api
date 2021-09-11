package com.idaltchion.ifxfood.core.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.idaltchion.ifxfood.core.email.EmailProperties.TipoImplementacao;
import com.idaltchion.ifxfood.domain.service.EnvioEmailService;
import com.idaltchion.ifxfood.infrastructure.service.email.FakeEnvioEmailService;
import com.idaltchion.ifxfood.infrastructure.service.email.SandboxEnvioEmailService;
import com.idaltchion.ifxfood.infrastructure.service.email.SmtpEnvioEmailService;

@Configuration
public class EmailConfig {

	@Autowired
	private EmailProperties emailProperties;
	
	@Bean
	public EnvioEmailService envioEmailService() {
		
		final TipoImplementacao tipoImplementacao = emailProperties.getTipoImplementacao();
		
		switch(tipoImplementacao) {
		case FAKE:
			return new FakeEnvioEmailService();
		case SMTP:
			return new SmtpEnvioEmailService();
		case SANDBOX:
			return new SandboxEnvioEmailService();
		default:
			return null;
		}
		
	}
	
}
