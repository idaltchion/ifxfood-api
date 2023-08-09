package com.idaltchion.ifxfood.api.v1.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "grupos")
@Getter
@Setter
public class GrupoDTO extends RepresentationModel<GrupoDTO> {

	@ApiModelProperty(example = "1")
	@NotNull
	private Long id;
	
	@ApiModelProperty(example = "Gerente")
	@NotBlank
	private String nome;
	
}
