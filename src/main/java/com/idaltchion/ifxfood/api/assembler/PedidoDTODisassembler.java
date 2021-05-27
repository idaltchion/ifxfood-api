package com.idaltchion.ifxfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.idaltchion.ifxfood.api.model.input.PedidoDTOInput;
import com.idaltchion.ifxfood.domain.model.Pedido;

@Component
public class PedidoDTODisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Pedido toDomain(PedidoDTOInput pedidoInput) {
		return modelMapper.map(pedidoInput, Pedido.class);
	}
	
	
	
}
