package com.idaltchion.ifxfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.idaltchion.ifxfood.api.controller.CidadeController;
import com.idaltchion.ifxfood.api.controller.FormaPagamentoController;
import com.idaltchion.ifxfood.api.controller.PedidoController;
import com.idaltchion.ifxfood.api.controller.RestauranteController;
import com.idaltchion.ifxfood.api.controller.RestauranteProdutoController;
import com.idaltchion.ifxfood.api.controller.UsuarioController;
import com.idaltchion.ifxfood.api.model.PedidoDTO;
import com.idaltchion.ifxfood.domain.model.Pedido;

@Component
public class PedidoDTOAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoDTO>{

	@Autowired
	private ModelMapper modelMapper;

	public PedidoDTOAssembler() {
		super(PedidoController.class, PedidoDTO.class);
	}

	@Override
	public PedidoDTO toModel(Pedido pedido) {
		PedidoDTO pedidoDTO = createModelWithId(pedido.getCodigo(), pedido);
		modelMapper.map(pedido, pedidoDTO);
		
		pedidoDTO.getFormaPagamento().add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(FormaPagamentoController.class).buscar(pedidoDTO.getFormaPagamento().getId())).withSelfRel());
		pedidoDTO.getEnderecoEntrega().getCidade().add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CidadeController.class).buscar(pedidoDTO.getEnderecoEntrega().getCidade().getId())).withSelfRel());
		pedidoDTO.getRestaurante().add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestauranteController.class).buscar(pedidoDTO.getRestaurante().getId())).withSelfRel());
		pedidoDTO.getCliente().add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).buscar(pedidoDTO.getCliente().getId())).withSelfRel());
		pedidoDTO.getItens().forEach(item -> 
			item.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestauranteProdutoController.class)
					.buscar(pedidoDTO.getRestaurante().getId(), item.getProdutoId()))
					.withSelfRel())
				);
		
		return pedidoDTO;
	}
	
	public PedidoDTO toModelWithCollectionRel(Pedido pedido) {
		PedidoDTO pedidoDTO = toModel(pedido);
		pedidoDTO.add(WebMvcLinkBuilder.linkTo(PedidoController.class).withRel(IanaLinkRelations.COLLECTION));
		
		return pedidoDTO;
	}
	
}
