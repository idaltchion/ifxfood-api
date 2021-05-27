package com.idaltchion.ifxfood.domain.model;

import java.util.Arrays;
import java.util.List;

public enum StatusPedido {

	CRIADO("Criado"),
	CONFIRMADO("Confirmado", CRIADO),
	ENTREGUE("Entregue", CONFIRMADO),
	CANCELADO("Cancelado", CRIADO);
	
	private String descricao;
	private List<StatusPedido> statusAnterioresPermitidos;

	private StatusPedido(String descricao, StatusPedido... statusAnterioresPermitidos) {
		this.descricao = descricao;
		this.statusAnterioresPermitidos = Arrays.asList(statusAnterioresPermitidos);
	}
	
	public String getDescricao() {
		return descricao;
	}

	boolean naoPodeAlterarPara(StatusPedido novoStatus) {
		return !novoStatus.statusAnterioresPermitidos.contains(this);
	}
	
}
