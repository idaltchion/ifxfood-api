package com.idaltchion.ifxfood.api.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.idaltchion.ifxfood.api.domain.model.Estado;
import com.idaltchion.ifxfood.api.domain.repository.EstadoRepository;

@Component
public class EstadoRepositoryImpl implements EstadoRepository {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Estado> listar() {
		return manager.createQuery("from Estado", Estado.class).getResultList() ;
	}

	@Override
	public Estado buscar(Long id) {
		return manager.find(Estado.class, id);
	}

	@Override
	@Transactional
	public Estado salvar(Estado estado) {
		return manager.merge(estado) ;
	}

	@Override
	@Transactional
	public void remover(Estado estado) {
		estado = this.buscar(estado.getId());
		manager.remove(estado);
	}

}
