package com.idaltchion.ifxfood.api.v1.openapi.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel("Page")
@Getter
@Setter
public class PageOpenApi<T> {

	private List<T> content;
	
	@ApiModelProperty(name = "Total de registros", example = "100")
	private Long totalElements;
	
	@ApiModelProperty(name = "Quantidade de registros por página", example = "20")
	private int size;
	
	@ApiModelProperty(name = "Quantidade de páginas", example = "5")
	private int totalPages;
	
	@ApiModelProperty(name = "Número da página atual (página inicial = 0)", example = "0")
	private int page;
	
}
