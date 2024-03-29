package com.idaltchion.ifxfood.api.v1.model;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SenhaDTOInput {

	@NotBlank
	private String senhaAtual;
	
	@NotBlank
	private String novaSenha;
	
}
