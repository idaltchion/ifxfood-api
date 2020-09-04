package com.idaltchion.ifxfood.api.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.idaltchion.ifxfood.api.domain.model.Restaurante;

@Repository
public interface RestauranteRepository extends 
	CustomJpaRepository<Restaurante, Long>, 
	RestauranteRepositoryCustomQueries,
	JpaSpecificationExecutor<Restaurante> {
	
	/* Utilizando a keyword "Between" do JPA para criar pesquisas de >= e <= */ 
	List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);
	
	/*
	 * Fazendo consulta usando JPQL
	 * - Para fazer o bind, adicionar o @Param para adicionar o nome do atributo desejado se o nome do parametro no metodo for diferente
	 * - Flexibilidade no nome do metodo
	 * - Consulta para fins de teste, nao utilizada em nenhum metodo
	 */
	@Query("from Restaurante where nome like %:nome% and cozinha.id = :id")
	List<Restaurante> consultarPorNomeComJPQL(String nome, @Param("id") Long cozinha);
	
	/*
	 * Fazendo consulta usando arquivo orm.xml externalizado
	 * - Flexibilidade no nome do metodo
	 * - Consulta para fins de teste, nao utilizada em nenhum metodo
	 */
	List<Restaurante> consultarPorNomeComArquivoExternalizado(String nome, @Param("id") Long cozinha);
	
}
