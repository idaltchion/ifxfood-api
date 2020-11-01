package com.idaltchion.ifxfood.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import com.idaltchion.ifxfood.domain.model.Restaurante;

public interface RestauranteRepositoryCustomQueries {

	List<Restaurante> procurarRestaurante(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);
	
	List<Restaurante> procurarPorRestauranteComCriteriaAPI(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);

	List<Restaurante> findComFreteGratis(String nome);
	
}