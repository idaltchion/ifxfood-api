package com.idaltchion.ifxfood.api.v1.openapi.model;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "RestauranteResumoOpenAPI")
@Getter
@Setter
public class RestauranteResumoOpenAPI {

	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "Restaurante Madalozo")
	private String nome;
	
	@ApiModelProperty(example = "4.50")
	private BigDecimal taxaFrete;
	
//	private CozinhaDTO cozinha;
	
}
