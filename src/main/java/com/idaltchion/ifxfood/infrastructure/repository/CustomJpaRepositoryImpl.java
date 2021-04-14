package com.idaltchion.ifxfood.infrastructure.repository;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import com.idaltchion.ifxfood.domain.repository.CustomJpaRepository;
/*
 * Implementacao da interface customizada "base" do JPA.
 * Objetivo eh disponibilizar consultas especificas, e que nao existem no JPA original, para todas as entidades da aplicacao 
 */
public class CustomJpaRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements CustomJpaRepository<T, ID> {

	private EntityManager manager;
	
	public CustomJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.manager = entityManager;
	}

	@Override
	public Optional<T> buscarPrimeiroRegistro() {
		String jpql = "from " + getDomainClass().getName();
		T entity = manager.createQuery(jpql, getDomainClass()).setMaxResults(1).getSingleResult();
		return Optional.ofNullable(entity);
	}

	@Override
	public void detach(Object entity) {
		manager.detach(entity);
	}
	
}
