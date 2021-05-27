package com.idaltchion.ifxfood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.idaltchion.ifxfood.api.assembler.PedidoDTOAssembler;
import com.idaltchion.ifxfood.api.assembler.PedidoDTODisassembler;
import com.idaltchion.ifxfood.api.assembler.PedidoResumoDTOAssembler;
import com.idaltchion.ifxfood.api.model.PedidoDTO;
import com.idaltchion.ifxfood.api.model.PedidoResumoDTO;
import com.idaltchion.ifxfood.api.model.input.PedidoDTOInput;
import com.idaltchion.ifxfood.domain.model.Pedido;
import com.idaltchion.ifxfood.domain.model.Usuario;
import com.idaltchion.ifxfood.domain.service.CadastroPedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	
	@Autowired
	CadastroPedidoService pedidoService;
	
	@Autowired
	PedidoDTOAssembler pedidoDTOAssembler;
	
	@Autowired
	PedidoDTODisassembler pedidoDTODisassembler;
	
	@Autowired
	PedidoResumoDTOAssembler pedidoResumoDTOAssembler;
	
	@GetMapping
	public List<PedidoResumoDTO> listar() {
		return pedidoResumoDTOAssembler.toCollectionDTO(pedidoService.listar());
	}
	
	@GetMapping("/{pedido_id}")
	public PedidoDTO buscar(@PathVariable Long pedido_id) {
		return pedidoDTOAssembler.toDTO(pedidoService.buscar(pedido_id));
	}
	
	@PutMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PedidoDTO emitirPedido(@RequestBody @Valid PedidoDTOInput pedidoInput) {
		Pedido novoPedido = pedidoDTODisassembler.toDomain(pedidoInput);
		//TODO: alterar para o usuario que esta logado na aplicação
		novoPedido.setCliente(new Usuario());
		novoPedido.getCliente().setId(1L);
		
		novoPedido = pedidoService.emitirPedido(novoPedido);
		return pedidoDTOAssembler.toDTO(novoPedido);
	}

}
