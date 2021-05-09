package com.idaltchion.ifxfood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.idaltchion.ifxfood.api.model.PedidoResumoDTO;
import com.idaltchion.ifxfood.domain.model.Pedido;

@Component
public class PedidoResumoDTOAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public List<PedidoResumoDTO> toCollectionDTO(List<Pedido> pedidos) {
		return pedidos.stream()
				.map(pedido -> toDTO(pedido))
				.collect(Collectors.toList());
	}

	public PedidoResumoDTO toDTO(Pedido pedido) {
		return modelMapper.map(pedido, PedidoResumoDTO.class);
	}
	
}
