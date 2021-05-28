package com.idaltchion.ifxfood.api.model.input;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoDTOInput {
	
	@Valid
	@NotNull
	private RestauranteIdDTOInput restaurante;
	
	@NotNull
	@Valid
	private FormaPagamentoIdDTOInput formaPagamento;
	
	@Size(min = 1)
	@NotNull
	@Valid
	private List<ItemPedidoDTOInput> itens;
	
	@NotNull
	@Valid
	private EnderecoDTOInput enderecoEntrega;
	
}
