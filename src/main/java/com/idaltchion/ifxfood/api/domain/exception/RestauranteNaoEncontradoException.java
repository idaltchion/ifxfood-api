package com.idaltchion.ifxfood.api.domain.exception;

public class RestauranteNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;
	
	public RestauranteNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public RestauranteNaoEncontradoException(Long id) {
		this(String.format("Nao existe Restaurante cadastrado com codigo %d", id));
	}
	
}
