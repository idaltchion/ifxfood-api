package com.idaltchion.ifxfood.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.idaltchion.ifxfood.domain.model.StatusPedido;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoDTO extends RepresentationModel<PedidoDTO> {

	@ApiModelProperty(value = "Codigo do pedido", example = "67a18e2f-9e9b-4f5e-812e-6140762b628d")
	private String codigo;
	
	@ApiModelProperty(value = "Status do pedido", example = "CRIADO")
	private StatusPedido status;
	
	@ApiModelProperty(value = "Valor total dos produtos do pedido", example = "19.90")
	private BigDecimal subtotal;
	
	@ApiModelProperty(value = "Valor do frete", example = "4.50")
	private BigDecimal taxaFrete;
	
	@ApiModelProperty(value = "Valor total dos produtos + frete", example = "24.40")
	private BigDecimal valorTotal;
	
	@ApiModelProperty(value = "Data de criação do pedido", example = "2021-11-07T08:30:00.000")
	private OffsetDateTime dataCriacao;
	
	@ApiModelProperty(value = "Data de confirmação do pedido", example = "2021-11-07T08:30:00.000")
	private OffsetDateTime dataConfirmacao;
	
	@ApiModelProperty(value = "Data de cancelamento do pedido", example = "2021-11-07T08:30:00.000")
	private OffsetDateTime dataCancelamento;
	
	@ApiModelProperty(value = "Data de entrega do pedido", example = "2021-11-07T08:30:00.000")
	private OffsetDateTime dataEntrega;
	
	private FormaPagamentoDTO formaPagamento;
	private EnderecoDTO enderecoEntrega;
	private RestauranteApenasNomeDTO restaurante;
	private UsuarioDTO cliente;
	
	@ApiModelProperty(value = "Itens do pedido")
	private List<ItemPedidoDTO> itens;
	
}
