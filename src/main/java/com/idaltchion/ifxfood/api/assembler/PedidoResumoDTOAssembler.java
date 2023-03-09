package com.idaltchion.ifxfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.idaltchion.ifxfood.api.IfxLinks;
import com.idaltchion.ifxfood.api.controller.PedidoController;
import com.idaltchion.ifxfood.api.model.PedidoResumoDTO;
import com.idaltchion.ifxfood.domain.model.Pedido;

@Component
public class PedidoResumoDTOAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoResumoDTO> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private IfxLinks ifxLinks;

	public PedidoResumoDTOAssembler() {
		super(PedidoController.class, PedidoResumoDTO.class);
	}

	@Override
	public PedidoResumoDTO toModel(Pedido pedido) {
		PedidoResumoDTO pedidoResumoDTO = createModelWithId(pedido.getCodigo(), pedido); 
		modelMapper.map(pedido, pedidoResumoDTO);
		
		pedidoResumoDTO.getRestaurante().add(ifxLinks.linkToRestaurantes(pedidoResumoDTO.getRestaurante().getId()));
		pedidoResumoDTO.getCliente().add(ifxLinks.linkToUsuarios(pedidoResumoDTO.getCliente().getId()));
		
		return pedidoResumoDTO;
	}
	
}
