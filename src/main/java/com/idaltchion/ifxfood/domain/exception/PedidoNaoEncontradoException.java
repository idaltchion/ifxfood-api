package com.idaltchion.ifxfood.domain.exception;

public class PedidoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;
	
	public PedidoNaoEncontradoException(String codigo_pedido) {
		super(String.format("Não existe pedido cadastrado com o código '%s'", codigo_pedido));
	}
	
}
