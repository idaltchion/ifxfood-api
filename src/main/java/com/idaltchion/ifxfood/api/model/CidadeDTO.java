package com.idaltchion.ifxfood.api.model;

import com.idaltchion.ifxfood.domain.model.Estado;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeDTO {

	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "Curitiba")
	private String nome;
	
	private Estado estado;
	
}
