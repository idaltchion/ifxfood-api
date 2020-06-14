package com.idaltchion.ifxfood.api.domain.repository;

import java.util.List;

import com.idaltchion.ifxfood.api.domain.model.Restaurante;

public interface RestauranteRepository {

	public List<Restaurante> listar();
	public Restaurante buscar(Long id);
	public Restaurante salvar(Restaurante restaurante);
	public void remover(Restaurante restaurante);
	
}
