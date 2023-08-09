package com.idaltchion.ifxfood.api.v2.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "cidades")
@Getter
@Setter
public class CidadeDTOV2 extends RepresentationModel<CidadeDTOV2> {

	@ApiModelProperty(example = "1")
	private Long idCidade;
	
	@ApiModelProperty(example = "Curitiba")
	private String nomeCidade;
	
	@ApiModelProperty(example = "3")
	private Long idEstado;
	
	@ApiModelProperty(example = "Parana")
	private String nomeEstado;
	
}
