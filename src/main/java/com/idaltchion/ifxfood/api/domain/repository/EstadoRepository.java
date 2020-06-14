package com.idaltchion.ifxfood.api.domain.repository;

import java.util.List;

import com.idaltchion.ifxfood.api.domain.model.Estado;

public interface EstadoRepository {

	public List<Estado> listar();
	public Estado buscar(Long id);
	public Estado salvar(Estado estado);
	public void remover(Estado estado);
	
}
