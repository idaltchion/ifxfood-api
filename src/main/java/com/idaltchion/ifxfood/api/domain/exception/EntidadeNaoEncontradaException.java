package com.idaltchion.ifxfood.api.domain.exception;

/*
 * ResponseStatusException: uma exception generica no qual eh possivel informar qq status e uma mensagem customizada 
 * (sem precisar da anotacao @ResponseStatus nessa classe), dessa maneira, fica sendo uma alternativa à criacao de uma classe especifica e customizada para exception
 * "ganha-se tempo", pois poderia ser chamada no controller ao inves dessa classe customizada.
 * Obs.: Classe refatorada para utilizar novamente a @ResponseStatus
 * Obs.: Classe refatorada para utilizar o tratamento global de exceptions do controlador (vide ApiExceptionHandler)
 */

public abstract class EntidadeNaoEncontradaException extends NegocioException {

	private static final long serialVersionUID = 1L;
	
	public EntidadeNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
}
