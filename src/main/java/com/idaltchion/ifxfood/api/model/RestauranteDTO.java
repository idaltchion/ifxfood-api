package com.idaltchion.ifxfood.api.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonView;
import com.idaltchion.ifxfood.api.model.view.RestauranteView;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteDTO {

	@JsonView({ RestauranteView.ApenasNome.class, RestauranteView.Resumo.class })
	private Long id;
	
	@JsonView({ RestauranteView.ApenasNome.class, RestauranteView.Resumo.class })
	private String nome;
	
	@JsonView(RestauranteView.Resumo.class)
	private BigDecimal taxaFrete;
	
	@JsonView(RestauranteView.Resumo.class)
	private CozinhaDTO cozinha;
	private Boolean ativo;
	private Boolean aberto;
	private EnderecoDTO endereco;
	
}
