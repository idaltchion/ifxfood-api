package com.idaltchion.ifxfood.api.model;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoDTO extends RepresentationModel<ItemPedidoDTO> {

	@ApiModelProperty(value = "Codigo do produto", example = "5")
	private Long produtoId;
	
	@ApiModelProperty(value = "Nome do produto", example = "Barreado")
	private String produtoNome;
	
	@ApiModelProperty(value = "Preco unitario do produto", example = "28.90")
	private BigDecimal precoUnitario;
	
	@ApiModelProperty(value = "Quantidade do produto", example = "2")
	private Integer quantidade;
	
	@ApiModelProperty(value = "Valor total dos produtos", example = "57.80")
	private BigDecimal precoTotal;
	
	private String observacao;
	
}
