package com.idaltchion.ifxfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.idaltchion.ifxfood.api.controller.PedidoController;
import com.idaltchion.ifxfood.api.controller.RestauranteController;
import com.idaltchion.ifxfood.api.controller.UsuarioController;
import com.idaltchion.ifxfood.api.model.PedidoResumoDTO;
import com.idaltchion.ifxfood.domain.model.Pedido;

@Component
public class PedidoResumoDTOAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoResumoDTO> {

	@Autowired
	private ModelMapper modelMapper;

	public PedidoResumoDTOAssembler() {
		super(PedidoController.class, PedidoResumoDTO.class);
	}

	@Override
	public PedidoResumoDTO toModel(Pedido pedido) {
		PedidoResumoDTO pedidoResumoDTO = createModelWithId(pedido.getCodigo(), pedido); 
		modelMapper.map(pedido, pedidoResumoDTO);
		
		pedidoResumoDTO.getRestaurante().add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestauranteController.class).buscar(pedidoResumoDTO.getRestaurante().getId())).withSelfRel());
		pedidoResumoDTO.getCliente().add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).buscar(pedidoResumoDTO.getCliente().getId())).withSelfRel());
		
		return pedidoResumoDTO;
	}
	
}
