package com.idaltchion.ifxfood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.idaltchion.ifxfood.api.v1.IfxLinks;
import com.idaltchion.ifxfood.api.v1.controller.PedidoController;
import com.idaltchion.ifxfood.api.v1.model.PedidoResumoDTO;
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
		
		pedidoResumoDTO.getRestaurante().add(ifxLinks.linkToRestaurante(pedidoResumoDTO.getRestaurante().getId()));
		pedidoResumoDTO.getCliente().add(ifxLinks.linkToUsuario(pedidoResumoDTO.getCliente().getId()));
		
		return pedidoResumoDTO;
	}
	
}
