package com.idaltchion.ifxfood.api.model.input;

import java.math.BigDecimal;

import javax.validation.Valid;
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
	@Valid
	private CozinhaIdDTOInput cozinha;
	
	@NotNull
	@Valid
	private EnderecoDTOInput endereco;
	
}
