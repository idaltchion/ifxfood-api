package com.idaltchion.ifxfood.infrastructure.repository.spec;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import com.idaltchion.ifxfood.domain.model.Restaurante;

public class RestauranteSpecs {
	
	/*
	 * A interface Specification possui um metodo que necessita ser implementado. 
	 * Abaixo isso foi resolvido utilizando lambda
	 */
	public static Specification<Restaurante> comFreteGratis() {
		return (root, query, criteriaBuilder) -> 
			criteriaBuilder.equal(root.get("taxaFrete"), BigDecimal.ZERO);
	}
	
	public static Specification<Restaurante> comNomeSemelhante(String nome) {
		return (root, query, criteriaBuilder) -> 
			criteriaBuilder.like(root.get("nome"), "%" + nome + "%");
	}
	
}
