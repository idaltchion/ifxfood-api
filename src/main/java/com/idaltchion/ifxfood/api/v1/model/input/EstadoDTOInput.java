package com.idaltchion.ifxfood.api.v1.model.input;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoDTOInput {

	@ApiModelProperty(value = "Nome do Estado", example = "Paraná", required = true)
	@NotBlank
	private String nome;
	
}
