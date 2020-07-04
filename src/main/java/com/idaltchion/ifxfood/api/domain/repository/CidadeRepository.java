package com.idaltchion.ifxfood.api.domain.repository;

import java.util.List;

import com.idaltchion.ifxfood.api.domain.model.Cidade;

public interface CidadeRepository {

	public List<Cidade> listar();
	public Cidade buscar(Long id);
	public Cidade salvar(Cidade cidade);
	public void remover(Long id);
	
}
