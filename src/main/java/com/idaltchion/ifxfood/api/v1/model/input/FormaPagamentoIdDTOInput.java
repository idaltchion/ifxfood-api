package com.idaltchion.ifxfood.api.v1.model.input;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagamentoIdDTOInput {

	@ApiModelProperty(example = "1", required = true)
	@NotNull
	private Long id;
	
}
