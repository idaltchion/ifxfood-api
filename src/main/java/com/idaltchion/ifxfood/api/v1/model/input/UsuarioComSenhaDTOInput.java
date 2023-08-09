package com.idaltchion.ifxfood.api.v1.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioComSenhaDTOInput extends UsuarioDTOInput {
	
	@NotBlank
	private String senha;
	
}
