package com.idaltchion.ifxfood.api.model.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoIdDTOInput {

	@NotNull
	private Long id;
}