package com.idaltchion.ifxfood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.idaltchion.ifxfood.domain.exception.ProdutoNaoEncontradoException;
import com.idaltchion.ifxfood.domain.model.Produto;
import com.idaltchion.ifxfood.domain.repository.ProdutoRepository;

@Service
public class CadastroProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Transactional
	public Produto salvar(Produto produto) {
		return produtoRepository.saveAndFlush(produto);
	}
	
	public Produto buscar(Long produtoId, Long restauranteId) {
		return produtoRepository.findByProdutoIdAndRestauranteId(produtoId, restauranteId)
				.orElseThrow(() -> new ProdutoNaoEncontradoException(produtoId, restauranteId));
	}

	@Transactional
	public void remover(Produto produto) {
		produtoRepository.delete(produto);
	}
	
}
