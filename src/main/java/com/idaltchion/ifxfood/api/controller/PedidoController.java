package com.idaltchion.ifxfood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idaltchion.ifxfood.api.assembler.PedidoDTOAssembler;
import com.idaltchion.ifxfood.api.model.PedidoDTO;
import com.idaltchion.ifxfood.domain.service.CadastroPedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	
	@Autowired
	CadastroPedidoService pedidoService;
	
	@Autowired
	PedidoDTOAssembler pedidoDTOAssembler;
	
	@GetMapping
	public List<PedidoDTO> listar() {
		return pedidoDTOAssembler.toCollectionDTO(pedidoService.listar());
	}
	
	@GetMapping("/{pedido_id}")
	public PedidoDTO buscar(@PathVariable Long pedido_id) {
		return pedidoDTOAssembler.toDTO(pedidoService.buscar(pedido_id));
	}

}
