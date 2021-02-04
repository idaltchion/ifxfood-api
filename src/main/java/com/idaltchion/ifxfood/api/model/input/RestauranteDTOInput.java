package com.idaltchion.ifxfood.api.model.input;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteDTOInput {

	@NotBlank
	private String nome;
	
	@PositiveOrZero
	@NotNull
	private BigDecimal taxaFrete;
	
	@NotNull
	private CozinhaDTOInput cozinha;
	
}
