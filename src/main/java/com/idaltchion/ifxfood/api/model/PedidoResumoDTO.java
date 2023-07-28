package com.idaltchion.ifxfood.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.idaltchion.ifxfood.domain.model.StatusPedido;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "pedidosResumo")
@Getter
@Setter
public class PedidoResumoDTO extends RepresentationModel<PedidoResumoDTO> {

	@ApiModelProperty(value = "Codigo do pedido", example = "cf43ed0a-12ac-4ebe-a0b5-b6b10465db66")
	private String codigo;
	
	@ApiModelProperty(value = "Status do pedido")
	private StatusPedido status;
	
	@ApiModelProperty(value = "Valor total dos produtos do pedido", example = "19.90")
	private BigDecimal subtotal;
	
	@ApiModelProperty(value = "Valor do frete", example = "4.50")
	private BigDecimal taxaFrete;
	
	@ApiModelProperty(value = "Valor total dos produtos + frete", example = "24.40")
	private BigDecimal valorTotal;
	
	@ApiModelProperty(value = "Data de criação do pedido", example = "2021-11-07T08:30:00.000")
	private OffsetDateTime dataCriacao;
	
	private RestauranteApenasNomeDTO restaurante;
	
	private UsuarioDTO cliente;
	
}
