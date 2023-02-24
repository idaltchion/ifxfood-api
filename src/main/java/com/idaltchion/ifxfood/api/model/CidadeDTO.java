package com.idaltchion.ifxfood.api.model;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeDTO extends RepresentationModel<CidadeDTO> {

	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "Curitiba")
	private String nome;
	
	private EstadoDTO estado;
	
}
