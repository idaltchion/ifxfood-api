package com.idaltchion.ifxfood.domain.exception;

import com.idaltchion.ifxfood.domain.model.Restaurante;

public class ProdutoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final String MSG_PRODUTO_NAO_ENCONTRADO = "Nenhum produto com codigo %d localizado para o restaurante de codido %d";
	private static final long serialVersionUID = 1L;
	
	public ProdutoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public ProdutoNaoEncontradoException(Long id, Restaurante restaurante) {
		this(String.format(MSG_PRODUTO_NAO_ENCONTRADO, id, restaurante.getId()));
	}
	
	public ProdutoNaoEncontradoException(Long id, Long restauranteId) {
		this(String.format(MSG_PRODUTO_NAO_ENCONTRADO, id, restauranteId));
	}
	
}
