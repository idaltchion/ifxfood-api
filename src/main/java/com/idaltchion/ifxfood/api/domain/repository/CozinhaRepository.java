package com.idaltchion.ifxfood.api.domain.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.idaltchion.ifxfood.api.domain.model.Cozinha;

public interface CozinhaRepository {

	public List<Cozinha> listar();
	public Cozinha buscar(Long id);
	public Cozinha salvar(Cozinha cozinha);
	public void remover(Cozinha cozinha);
	
}
