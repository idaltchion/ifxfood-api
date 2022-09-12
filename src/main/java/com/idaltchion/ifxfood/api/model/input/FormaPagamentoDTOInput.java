package com.idaltchion.ifxfood.api.model.input;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagamentoDTOInput {

	@ApiModelProperty(example = "Cartão de débito", required = true)
	@NotBlank
	private String descricao;
	
}
