package com.idaltchion.ifxfood.api.model;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteResumoDTO extends RepresentationModel<RestauranteResumoDTO> {

	@ApiModelProperty(example = "2")
	private Long id;
	
	@ApiModelProperty(example = "Restaurante Madaloso")
	private String nome;
	
}
