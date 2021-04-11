package com.idaltchion.ifxfood.domain.exception;

public class UsuarioNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;
	private static final String MSG_USUARIO_NAO_ENCONTRADO = "Não existe Usuario cadastrado com código %d";
	
	public UsuarioNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public UsuarioNaoEncontradoException(Long id) {
		this(String.format(MSG_USUARIO_NAO_ENCONTRADO, id));
	}

}
