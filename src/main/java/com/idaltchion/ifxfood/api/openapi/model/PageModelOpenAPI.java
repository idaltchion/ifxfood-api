package com.idaltchion.ifxfood.api.openapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "PageModel")
@Getter
@Setter
public class PageModelOpenAPI {
	@ApiModelProperty(name = "Total de registros", example = "100")
	private Long totalElements;
	
	@ApiModelProperty(name = "Quantidade de registros por página", example = "20")
	private int size;
	
	@ApiModelProperty(name = "Quantidade de páginas", example = "5")
	private int totalPages;
	
	@ApiModelProperty(name = "Número da página atual (página inicial = 0)", example = "0")
	private int number;

}
