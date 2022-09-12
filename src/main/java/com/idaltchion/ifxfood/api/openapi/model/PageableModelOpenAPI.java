package com.idaltchion.ifxfood.api.openapi.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "Pageable")
@Getter
@Setter
public class PageableModelOpenAPI {

	@ApiModelProperty(value = "Número de elementos a serem exibidos em uma página", example = "10")
	private int size;
	
	@ApiModelProperty(value = "Número da página (primeira página = 0)", example = "0")
	private int page;
	
	@ApiModelProperty(value = "Atributos a serem ordenados seguido do tipo da ordenação (asc: ascendente (default), desc: descendente", example = "nome,desc")
	private List<String> sort;
	
}
