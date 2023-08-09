package com.idaltchion.ifxfood.api.v1.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "usuarios")
@Getter
@Setter
public class UsuarioDTO extends RepresentationModel<UsuarioDTO> {

	@ApiModelProperty(example = "3")
	private Long id;
	
	@ApiModelProperty(example = "Maria Joaquina")
	private String nome;
	
	@ApiModelProperty(example = "maria.joaquina@email.com")
	private String email;
	
}
