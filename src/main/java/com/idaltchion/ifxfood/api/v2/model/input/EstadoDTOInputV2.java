package com.idaltchion.ifxfood.api.v2.model.input;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoDTOInputV2 {

	@ApiModelProperty(value = "Nome do Estado", example = "Paraná", required = true)
	@NotBlank
	private String nomeEstado;
	
}
