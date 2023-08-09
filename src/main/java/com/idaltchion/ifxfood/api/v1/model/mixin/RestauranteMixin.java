package com.idaltchion.ifxfood.api.v1.model.mixin;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.idaltchion.ifxfood.domain.model.Cozinha;
import com.idaltchion.ifxfood.domain.model.Endereco;
import com.idaltchion.ifxfood.domain.model.FormaPagamento;
import com.idaltchion.ifxfood.domain.model.Produto;

public abstract class RestauranteMixin {

	/*
	 * @JsonIgnoreProperties(value = "nome", allowGetters = true)
	 * faz com que o atributo nome possa ser utilizado/mostrado somente na consulta e nao em uma atualizacao de restaurante 
	 */
	@JsonIgnoreProperties(value = "nome", allowGetters = true) 
	private Cozinha cozinha;

	//para nao mostrar no payload da lista de restaurantes
	@JsonIgnore
	private List<FormaPagamento> formasPagamento = new ArrayList<>();
	
	@JsonIgnore
	private Endereco endereco;
	
	@JsonIgnore
	private OffsetDateTime dataCadastro;
	
	@JsonIgnore
	private OffsetDateTime dataAtualizacao;
	
	@JsonIgnore //para evitar problema de referencia circular, pois o relacionamento e bi-direcional
	private List<Produto> produtos = new ArrayList<>();
	
}
