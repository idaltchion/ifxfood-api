package com.idaltchion.ifxfood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {

	@ApiModelProperty(example = "3")
	private Long id;
	
	@ApiModelProperty(example = "Maria Joaquina")
	private String nome;
	
	@ApiModelProperty(example = "maria.joaquina@email.com")
	private String email;
	
}
