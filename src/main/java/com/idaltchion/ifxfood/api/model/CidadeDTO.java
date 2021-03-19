package com.idaltchion.ifxfood.api.model;

import com.idaltchion.ifxfood.domain.model.Estado;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeDTO {

	private Long id;
	private String nome;
	private Estado estado;
	
}
