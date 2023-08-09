package com.idaltchion.ifxfood.api.v1.model;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeResumoDTO extends RepresentationModel<CidadeResumoDTO>{

	@ApiModelProperty(example = "2")
	private Long id;
	
	@ApiModelProperty(example = "Curitiba")
	private String nome;
	
	@ApiModelProperty(example = "Paraná")
	private String estado;
	
}
