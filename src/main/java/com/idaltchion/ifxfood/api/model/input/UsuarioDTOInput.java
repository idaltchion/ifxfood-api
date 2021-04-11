package com.idaltchion.ifxfood.api.model.input;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTOInput {

	@NotBlank
	private String nome;
	
	@NotBlank
	@Email
	private String email;
	
}
