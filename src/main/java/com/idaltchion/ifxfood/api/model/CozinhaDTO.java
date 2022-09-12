package com.idaltchion.ifxfood.api.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.idaltchion.ifxfood.api.model.view.RestauranteView;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaDTO {

	@ApiModelProperty(example = "1")
	@JsonView(RestauranteView.Resumo.class)
	private Long id;
	
	@ApiModelProperty(example = "Italiana")
	@JsonView(RestauranteView.Resumo.class)
	private String nome;
	
}
