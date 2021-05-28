package com.idaltchion.ifxfood.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.idaltchion.ifxfood.domain.model.StatusPedido;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoResumoDTO {

	private String codigo;
	private StatusPedido status;
	private BigDecimal subtotal;
	private BigDecimal taxaFrete;
	private BigDecimal valorTotal;
	private OffsetDateTime dataCriacao;
	private RestauranteResumoDTO restaurante;
	private UsuarioDTO cliente;
	
}
