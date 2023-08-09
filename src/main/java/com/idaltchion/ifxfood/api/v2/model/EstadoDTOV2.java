package com.idaltchion.ifxfood.api.v2.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "estados")
@Getter
@Setter
public class EstadoDTOV2 extends RepresentationModel<EstadoDTOV2>{

	@ApiModelProperty(example = "3")
	private Long idEstado;
	
	@ApiModelProperty(example = "Paraná")
	private String nomeEstado;
	
}
