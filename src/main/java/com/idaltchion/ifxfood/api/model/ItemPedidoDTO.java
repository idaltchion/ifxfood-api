package com.idaltchion.ifxfood.api.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoDTO {

	private String produtoNome;
	private BigDecimal produtoPreco;
	private BigDecimal precoUnitario;
	private Integer quantidade;
	private BigDecimal precoTotal;
	private String observacao;
	
}
