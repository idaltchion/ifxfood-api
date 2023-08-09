package com.idaltchion.ifxfood.api.v1.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.idaltchion.ifxfood.domain.model.Estado;

public abstract class CidadeMixin {

	@JsonIgnoreProperties(value = "nome", allowGetters = true)
	private Estado estado;
	
}
