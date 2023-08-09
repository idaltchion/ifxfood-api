package com.idaltchion.ifxfood.api.v2.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeDTOInputV2 {

	@ApiModelProperty(example = "Curitiba")
	@NotBlank
	private String nomeCidade;
	
	@ApiModelProperty(example = "3")
	@NotNull
	private Long idEstado;
}
