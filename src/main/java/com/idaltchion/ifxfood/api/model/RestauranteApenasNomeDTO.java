package com.idaltchion.ifxfood.api.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "restaurantes")
@Getter
@Setter
public class RestauranteApenasNomeDTO extends RepresentationModel<RestauranteApenasNomeDTO> {

	@ApiModelProperty(example = "2")
	private Long id;
	
	@ApiModelProperty(example = "Restaurante Madalozo")
	private String nome;
	
}
