package com.idaltchion.ifxfood.domain.exception;

public class PermissaoNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;
	
	public PermissaoNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

	public PermissaoNaoEncontradaException(Long id) {
		this(String.format("Não existe permissão cadastrada com o código %d", id));
	}

}
