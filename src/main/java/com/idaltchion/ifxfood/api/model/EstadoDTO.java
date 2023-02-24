package com.idaltchion.ifxfood.api.model;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoDTO extends RepresentationModel<EstadoDTO>{

	@ApiModelProperty(example = "3")
	private Long id;
	
	@ApiModelProperty(example = "Paraná")
	private String nome;
	
}
