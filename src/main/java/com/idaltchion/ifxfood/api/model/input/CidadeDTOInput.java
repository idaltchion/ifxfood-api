package com.idaltchion.ifxfood.api.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeDTOInput {

	@NotBlank
	private String nome;
	
	@Valid
	@NotNull
	private EstadoIdDTOInput estado;
}
