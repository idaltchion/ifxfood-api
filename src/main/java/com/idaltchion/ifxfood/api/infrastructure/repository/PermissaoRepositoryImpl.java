package com.idaltchion.ifxfood.api.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.idaltchion.ifxfood.api.domain.model.Permissao;
import com.idaltchion.ifxfood.api.domain.repository.PermissaoRespository;

@Component
public class PermissaoRepositoryImpl implements PermissaoRespository {

	private EntityManager manager;
	
	@Override
	public List<Permissao> listar() {
		return manager.createQuery("from Permissao", Permissao.class).getResultList();
	}

	@Override
	public Permissao buscar(Long id) {
		return manager.find(Permissao.class, id);
	}

	@Override
	@Transactional
	public Permissao salvar(Permissao permissao) {
		return manager.merge(permissao);
	}

	@Override
	@Transactional
	public void remover(Permissao permissao) {
		permissao = this.buscar(permissao.getId());
		manager.remove(permissao);
	}

}
