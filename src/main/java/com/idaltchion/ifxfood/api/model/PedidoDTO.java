package com.idaltchion.ifxfood.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import com.idaltchion.ifxfood.domain.model.StatusPedido;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoDTO {

	private String codigo;
	private StatusPedido status;
	private BigDecimal subtotal;
	private BigDecimal taxaFrete;
	private BigDecimal valorTotal;
	private OffsetDateTime dataCriacao;
	private OffsetDateTime dataConfirmacao;
	private OffsetDateTime dataCancelamento;
	private OffsetDateTime dataEntrega;
	private FormaPagamentoDTO formaPagamento;
	private EnderecoDTO enderecoEntrega;
	private RestauranteResumoDTO restaurante;
	private UsuarioDTO cliente;
	private List<ItemPedidoDTO> itens;
	
}
