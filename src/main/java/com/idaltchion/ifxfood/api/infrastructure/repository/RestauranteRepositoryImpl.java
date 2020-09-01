package com.idaltchion.ifxfood.api.infrastructure.repository;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.idaltchion.ifxfood.api.domain.model.Restaurante;
import com.idaltchion.ifxfood.api.domain.repository.RestauranteRepositoryCustomQueries;

/*
 * Classe criada para possibilitar consultas SQL mais dinamicas
 * - O nome da classe deve conter o sufixo "Impl" para dar match com a classe RestauranteRepository 
 */
@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryCustomQueries {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Restaurante> procurarRestaurante(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
		var jpql = "from Restaurante where nome like :nome "
				+ "and taxaFrete between :taxaFreteInicial and :taxaFreteFinal";
		
		return manager.createQuery(jpql, Restaurante.class)
				.setParameter("nome", "%" + nome + "%")
				.setParameter("taxaFreteInicial", taxaFreteInicial)
				.setParameter("taxaFreteFinal", taxaFreteFinal)
				.getResultList();
				
	}

}
