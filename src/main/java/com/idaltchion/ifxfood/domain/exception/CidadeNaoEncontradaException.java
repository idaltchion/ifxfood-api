package com.idaltchion.ifxfood.domain.exception;

public class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;
	
	public CidadeNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public CidadeNaoEncontradaException(Long id) {
		this(String.format("Nao existe Cidade cadastrada com codigo %d", id));
	}
	
}
