package com.idaltchion.ifxfood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.idaltchion.ifxfood.api.v1.IfxLinks;
import com.idaltchion.ifxfood.api.v1.controller.PedidoController;
import com.idaltchion.ifxfood.api.v1.model.PedidoDTO;
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
				
		pedidoDTO.getFormaPagamento().add(ifxLinks.linkToFormaPagamento(pedidoDTO.getFormaPagamento().getId()));
		pedidoDTO.getEnderecoEntrega().getCidade().add(ifxLinks.linkToCidade(pedidoDTO.getEnderecoEntrega().getCidade().getId()));
		pedidoDTO.getRestaurante().add(ifxLinks.linkToRestaurante(pedidoDTO.getRestaurante().getId()));
		pedidoDTO.getCliente().add(ifxLinks.linkToUsuario(pedidoDTO.getCliente().getId()));
		pedidoDTO.getItens().forEach(item -> 
			item.add(ifxLinks.linkToProduto(pedidoDTO.getRestaurante().getId(), item.getProdutoId(), IanaLinkRelations.SELF_VALUE)));
		return pedidoDTO;
	}
	
	public PedidoDTO toModelWithCollectionRel(Pedido pedido) {
		PedidoDTO pedidoDTO = toModel(pedido);
		pedidoDTO.add(ifxLinks.linkToPedidos("pedidos"));
		
		if (pedido.podeSerConfirmado()) {
			pedidoDTO.add(ifxLinks.linkToConfirmacaoPedido(pedidoDTO.getCodigo()));
		}
		
		if (pedido.podeSerEntregue()) {
			pedidoDTO.add(ifxLinks.linkToEntregaPedido(pedidoDTO.getCodigo()));
		}
		
		if (pedido.podeSerCancelado()) {
			pedidoDTO.add(ifxLinks.linkToCancelamentoPedido(pedidoDTO.getCodigo()));
		}
		
		return pedidoDTO;
	}
	
}
