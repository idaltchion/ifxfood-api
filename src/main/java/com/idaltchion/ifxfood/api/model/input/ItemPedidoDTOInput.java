package com.idaltchion.ifxfood.api.model.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoDTOInput {

	@NotNull
	private Long produtoId;
	
	@NotNull
	private Integer quantidade;
//	private BigDecimal precoUnitario;
//	private BigDecimal precoTotal;
	private String observacao;
//	private Produto produto;
	
}
