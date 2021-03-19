package com.idaltchion.ifxfood.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoDTOInput {

	@NotBlank
	private String nome;
	
}
