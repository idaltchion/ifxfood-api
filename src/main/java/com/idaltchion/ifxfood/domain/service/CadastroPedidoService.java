package com.idaltchion.ifxfood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idaltchion.ifxfood.domain.exception.PedidoNaoEncontradoException;
import com.idaltchion.ifxfood.domain.model.Pedido;
import com.idaltchion.ifxfood.domain.repository.PedidoRepository;

@Service
public class CadastroPedidoService {

	@Autowired
	PedidoRepository pedidoRepository;
	
	public List<Pedido> listar() {
		return pedidoRepository.findAll();
	}

	public Pedido buscar(Long id) {
		return pedidoRepository.findById(id).orElseThrow(
				() -> new PedidoNaoEncontradoException(id));
	}
	
}
