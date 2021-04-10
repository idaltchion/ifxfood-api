package com.idaltchion.ifxfood.domain.exception;

public class GrupoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;
	
	public GrupoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public GrupoNaoEncontradoException(Long id) {
		this(String.format("Não existe grupo cadastrado com o codigo %d", id));
	}
	
}
