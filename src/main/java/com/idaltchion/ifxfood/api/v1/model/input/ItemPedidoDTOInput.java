package com.idaltchion.ifxfood.api.v1.model.input;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoDTOInput {

	@ApiModelProperty(example = "9", required = true)
	@NotNull
	private Long produtoId;
	
	@ApiModelProperty(example = "3", required = true)
	@NotNull
	private Integer quantidade;
	
	@ApiModelProperty(example = "sem molho de pimenta")
	private String observacao;
	
}
