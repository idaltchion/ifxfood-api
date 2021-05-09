package com.idaltchion.ifxfood.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.idaltchion.ifxfood.domain.model.StatusPedido;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoResumoDTO {

	private Long id;
	private BigDecimal subtotal;
	private BigDecimal taxaFrete;
	private BigDecimal valorTotal;
	private OffsetDateTime dataCriacao;
	private RestauranteResumoDTO restaurante;
	private StatusPedido status;
	private UsuarioDTO cliente;
	
}
