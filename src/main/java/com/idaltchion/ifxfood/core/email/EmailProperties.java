package com.idaltchion.ifxfood.core.email;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
@Validated
@ConfigurationProperties("ifxfood.mail")
public class EmailProperties {

	@NotNull
	private String remetente;
	
	private Sandbox sandbox;
	
	private TipoImplementacao tipoImplementacao = TipoImplementacao.FAKE; 
			
	public enum TipoImplementacao {
		SMTP, FAKE, SANDBOX;
	}
	
	@Getter
	@Setter
	public class Sandbox {
		private String destinatario;
	}
	
}
