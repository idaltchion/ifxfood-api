package com.idaltchion.ifxfood.api.v1.model.input;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteDTOInput {

	@ApiModelProperty(example = "Restaurante Madalozo", required = true)
	@NotBlank
	private String nome;
	
	@ApiModelProperty(example = "3.90", required = true)
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
