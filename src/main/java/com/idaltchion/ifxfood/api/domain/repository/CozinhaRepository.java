package com.idaltchion.ifxfood.api.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.idaltchion.ifxfood.api.domain.model.Cozinha;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {

	/*
	 * Query JPA
	 * Existem varios prefixos que podem ser utilizados no JPA
	 * ex: find, query, get, stream
	 * 
	 * Outras keywords que podem ser utilizadas:
	 * TopX: retorna as top x itens que deseja pesquisar. Ex: findTop5Descricao
	 * count: retorna a quantidade de itens que deseja pesquisar. Ex: countByNome
	 * exists: retorna um booleano se o item que deseja pesquisar existe ou nao.
	 * 
	 * referencia: https://docs.spring.io/spring-data/jpa/docs/2.3.1.RELEASE/reference/html/#reference
	 */
	
	
	/* Utilizando keywork "findBy" query para fazer pesquisas - existem outras na doc do Spring
	 * keyword: findBy
	 * atributo da pesquisa: Nome *precisa ser o mesmo nome existente na propriedade da classe  
	 *  */
	List<Cozinha> findByNome(String nome);
	
	/* Nesse caso, utilizando a keyword "Containing" a consulta é igual à utilização de 'like' */
	List<Cozinha> findByNomeContaining(String nome);
	
}
