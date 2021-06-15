package com.idaltchion.ifxfood.domain.repository;

import com.idaltchion.ifxfood.domain.model.FotoProduto;

public interface FotoProdutoCustomQueries {
	
	FotoProduto save(FotoProduto foto);
	
	void delete(FotoProduto foto);
	
}
