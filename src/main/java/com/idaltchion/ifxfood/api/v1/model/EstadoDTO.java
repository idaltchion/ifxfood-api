package com.idaltchion.ifxfood.api.v1.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "estados")
@Getter
@Setter
public class EstadoDTO extends RepresentationModel<EstadoDTO>{

	@ApiModelProperty(example = "3")
	private Long id;
	
	@ApiModelProperty(example = "Paraná")
	private String nome;
	
}
