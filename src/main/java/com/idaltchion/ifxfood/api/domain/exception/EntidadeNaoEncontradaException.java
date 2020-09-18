package com.idaltchion.ifxfood.api.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
 * ResponseStatusException: uma exception generica no qual eh possivel informar qq status e uma mensagem customizada 
 * (sem precisar da anotacao @ResponseStatus nessa classe), dessa maneira, fica sendo uma alternativa à criacao de uma classe especifica e customizada para exception
 * "ganha-se tempo", pois poderia ser chamada no controller ao inves dessa classe customizada.
 * Obs.: Refatorada para utilizar novamente a @ResponseStatus
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class EntidadeNaoEncontradaException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public EntidadeNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
}
