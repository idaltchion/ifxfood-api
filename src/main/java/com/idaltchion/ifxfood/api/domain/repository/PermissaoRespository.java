package com.idaltchion.ifxfood.api.domain.repository;

import java.util.List;

import com.idaltchion.ifxfood.api.domain.model.Permissao;

public interface PermissaoRespository {

	public List<Permissao> listar();
	public Permissao buscar(Long id);
	public Permissao salvar(Permissao permissao);
	public void remover(Permissao permissao);
	
}
