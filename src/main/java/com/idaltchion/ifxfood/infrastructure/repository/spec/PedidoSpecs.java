package com.idaltchion.ifxfood.infrastructure.repository.spec;

import java.util.ArrayList;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.idaltchion.ifxfood.domain.filter.PedidoFilter;
import com.idaltchion.ifxfood.domain.model.Pedido;

public class PedidoSpecs {

	public static Specification<Pedido> usandoFiltro(PedidoFilter filtro) {
		return (root, criteriaQuery, criteriaBuilder) -> {
			var predicates = new ArrayList<Predicate>();
			if (Pedido.class.equals(criteriaQuery.getResultType())) {
				/* o fetch resolve problema do n + 1 */
				root.fetch("restaurante").fetch("cozinha");
				root.fetch("cliente");
			}
			
			if (filtro.getClienteId() != null) {
				predicates.add(criteriaBuilder.equal(root.get("cliente"), filtro.getClienteId()));
			}
			
			if (filtro.getRestauranteId() != null) {
				predicates.add(criteriaBuilder.equal(root.get("restaurante"), filtro.getRestauranteId()));
			}
			
			if (filtro.getDataCriacaoInicio() != null) {
				predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoInicio()));
			}
			
			if (filtro.getDataCriacaoFim() != null) {
				predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoFim()));
			}
			
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
	}
}
