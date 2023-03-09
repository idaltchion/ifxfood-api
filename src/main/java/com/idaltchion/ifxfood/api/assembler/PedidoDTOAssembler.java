package com.idaltchion.ifxfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.idaltchion.ifxfood.api.IfxLinks;
import com.idaltchion.ifxfood.api.controller.PedidoController;
import com.idaltchion.ifxfood.api.model.PedidoDTO;
import com.idaltchion.ifxfood.domain.model.Pedido;

@Component
public class PedidoDTOAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoDTO>{

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private IfxLinks ifxLinks;

	public PedidoDTOAssembler() {
		super(PedidoController.class, PedidoDTO.class);
	}

	@Override
	public PedidoDTO toModel(Pedido pedido) {
		PedidoDTO pedidoDTO = createModelWithId(pedido.getCodigo(), pedido);
		modelMapper.map(pedido, pedidoDTO);
				
		pedidoDTO.getFormaPagamento().add(ifxLinks.linkToFormasPagamento(pedidoDTO.getFormaPagamento().getId()));
		pedidoDTO.getEnderecoEntrega().getCidade().add(ifxLinks.linkToCidades(pedidoDTO.getEnderecoEntrega().getCidade().getId()));
		pedidoDTO.getRestaurante().add(ifxLinks.linkToRestaurantes(pedidoDTO.getRestaurante().getId()));
		pedidoDTO.getCliente().add(ifxLinks.linkToUsuarios(pedidoDTO.getCliente().getId()));
		pedidoDTO.getItens().forEach(item -> 
			item.add(ifxLinks.linkToProdutos(pedidoDTO.getRestaurante().getId(), item.getProdutoId())));
		return pedidoDTO;
	}
	
	public PedidoDTO toModelWithCollectionRel(Pedido pedido) {
		PedidoDTO pedidoDTO = toModel(pedido);
		pedidoDTO.add(ifxLinks.linkToPedidos());
		
		return pedidoDTO;
	}
	
}
