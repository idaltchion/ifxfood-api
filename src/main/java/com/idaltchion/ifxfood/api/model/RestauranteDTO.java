package com.idaltchion.ifxfood.api.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonView;
import com.idaltchion.ifxfood.api.model.view.RestauranteView;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteDTO {

	@ApiModelProperty(example = "2")
	@JsonView({ RestauranteView.ApenasNome.class, RestauranteView.Resumo.class })
	private Long id;
	
	@ApiModelProperty(example = "Restaurante Madalozo")
	@JsonView({ RestauranteView.ApenasNome.class, RestauranteView.Resumo.class })
	private String nome;
	
	@ApiModelProperty(example = "4.50")
	@JsonView(RestauranteView.Resumo.class)
	private BigDecimal taxaFrete;
	
	@JsonView(RestauranteView.Resumo.class)
	private CozinhaDTO cozinha;
	private Boolean ativo;
	private Boolean aberto;
	private EnderecoDTO endereco;
	
}
