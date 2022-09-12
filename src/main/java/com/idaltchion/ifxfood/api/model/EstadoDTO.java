package com.idaltchion.ifxfood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoDTO {

	@ApiModelProperty(example = "3")
	private Long id;
	
	@ApiModelProperty(example = "Paraná")
	private String nome;
	
}
