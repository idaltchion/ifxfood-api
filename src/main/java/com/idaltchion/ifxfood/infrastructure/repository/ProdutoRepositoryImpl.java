package com.idaltchion.ifxfood.infrastructure.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.idaltchion.ifxfood.domain.model.FotoProduto;
import com.idaltchion.ifxfood.domain.repository.FotoProdutoCustomQueries;

@Repository
public class ProdutoRepositoryImpl implements FotoProdutoCustomQueries {

	@PersistenceContext
	private EntityManager manager;
	
	@Transactional
	@Override
	public FotoProduto save(FotoProduto foto) {
		return manager.merge(foto);
	}

	@Transactional
	@Override
	public void delete(FotoProduto foto) {
		manager.remove(foto);
	}

	
}
