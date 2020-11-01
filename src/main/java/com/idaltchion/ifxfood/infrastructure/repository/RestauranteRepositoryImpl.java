package com.idaltchion.ifxfood.infrastructure.repository;

import static com.idaltchion.ifxfood.infrastructure.repository.spec.RestauranteSpecs.comFreteGratis;
import static com.idaltchion.ifxfood.infrastructure.repository.spec.RestauranteSpecs.comNomeSemelhante;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.idaltchion.ifxfood.domain.model.Restaurante;
import com.idaltchion.ifxfood.domain.repository.RestauranteRepository;
import com.idaltchion.ifxfood.domain.repository.RestauranteRepositoryCustomQueries;
import com.idaltchion.ifxfood.infrastructure.repository.spec.RestauranteSpecs;

/*
 * Classe criada para possibilitar consultas SQL mais dinamicas
 * - O nome da classe deve conter o sufixo "Impl" para dar match com a classe RestauranteRepository 
 */
@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryCustomQueries {
	
	@PersistenceContext
	private EntityManager manager;
	
	/* A anotacao Lazy, nesse caso, resolve o problema de referencia circular */
	@Autowired @Lazy
	private RestauranteRepository restauranteRepository;
	
	@Override
	public List<Restaurante> procurarRestaurante(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
		
		var jpql = new StringBuilder();
		var parametros = new HashMap<String, Object>();
		
		jpql.append("from Restaurante where 0 = 0 ");
		
		if (StringUtils.hasLength("nome")) {
			jpql.append("and nome = " + nome);
			parametros.put("nome", "%" + nome + "%");
		}
		
		if (taxaFreteInicial != null) {
			jpql.append("and taxaFrete >= " + taxaFreteInicial);
			parametros.put("taxaFreteInicial", taxaFreteInicial);
		}
		
		if (taxaFreteFinal != null) {
			jpql.append("and taxaFrete <= " + taxaFreteFinal);
			parametros.put("taxaFreteFinal", taxaFreteFinal);
		}
		
		TypedQuery<Restaurante> query = manager.createQuery(jpql.toString(), Restaurante.class);
		parametros.forEach((parametro, valor) -> query.setParameter(parametro, valor));
		
		return query.getResultList();
				
	}

	@Override
	public List<Restaurante> procurarPorRestauranteComCriteriaAPI(String nome, BigDecimal taxaFreteInicial,
			BigDecimal taxaFreteFinal) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Restaurante> criteriaQuery = builder.createQuery(Restaurante.class);
		Root<Restaurante> root = criteriaQuery.from(Restaurante.class);
		
		var predicates = new ArrayList<Predicate>();
		
		if (StringUtils.hasLength(nome)) {
			predicates.add(builder.like(root.get("nome"), "%" + nome + "%"));			
		}
		
		if (taxaFreteInicial != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteInicial));			
		}
		
		if (taxaFreteFinal != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get("taxaFrete"), taxaFreteFinal));			
		}
		
		criteriaQuery.where(predicates.toArray(new Predicate[0]));
		
		TypedQuery<Restaurante> query = manager.createQuery(criteriaQuery);
		
		return query.getResultList();
	}

	@Override
	public List<Restaurante> findComFreteGratis(String nome) {
		return restauranteRepository.findAll(comFreteGratis().and(comNomeSemelhante(nome)));
	}
	

}
