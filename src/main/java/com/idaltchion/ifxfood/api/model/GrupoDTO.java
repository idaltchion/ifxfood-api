package com.idaltchion.ifxfood.api.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GrupoDTO {

	@NotNull
	private Long id;
	
	@NotBlank
	private String nome;
	
}
