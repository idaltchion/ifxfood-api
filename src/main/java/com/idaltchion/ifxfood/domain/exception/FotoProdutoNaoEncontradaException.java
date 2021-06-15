package com.idaltchion.ifxfood.domain.exception;

public class FotoProdutoNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final String MSG_FOTO_PRODUTO_NAO_ENCONTRADA = "Nenhuma foto foi encontrada para o produto %d do restaurante %d";
	private static final long serialVersionUID = 1L;
	
	public FotoProdutoNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

	public FotoProdutoNaoEncontradaException(Long restauranteId, Long produtoId) {
		this(String.format(MSG_FOTO_PRODUTO_NAO_ENCONTRADA, produtoId, restauranteId));
	}

}
