package com.idaltchion.ifxfood.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.idaltchion.ifxfood.domain.model.FotoProduto;
import com.idaltchion.ifxfood.domain.model.Produto;
import com.idaltchion.ifxfood.domain.model.Restaurante;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>, FotoProdutoCustomQueries {

	@Query("from produto where id = :produtoId and restaurante.id = :restauranteId")
	Optional<Produto> findByProdutoIdAndRestauranteId(
			@Param("produtoId") Long id, 
			@Param("restauranteId") Long restauranteId);
	
	List<Produto> findAllByRestaurante(Restaurante restaurante);

	@Query("from produto p where p.ativo = true and p.restaurante = :restaurante")
	List<Produto> findAtivosByRestaurante(Restaurante restaurante);
	
	@Query("select f from FotoProduto f join f.produto p where f.produto.id = :produtoId and p.restaurante.id = :restauranteId")
	Optional<FotoProduto> findFotoById(Long restauranteId, Long produtoId);
	
}
