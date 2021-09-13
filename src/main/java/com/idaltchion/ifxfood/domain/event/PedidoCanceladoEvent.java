package com.idaltchion.ifxfood.domain.event;

import com.idaltchion.ifxfood.domain.model.Pedido;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PedidoCanceladoEvent {
	
	private Pedido pedido;

}
