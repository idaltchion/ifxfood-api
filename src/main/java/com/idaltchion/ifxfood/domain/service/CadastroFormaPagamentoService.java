package com.idaltchion.ifxfood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idaltchion.ifxfood.domain.exception.FormaPagamentoNaoEncontradaException;
import com.idaltchion.ifxfood.domain.model.FormaPagamento;
import com.idaltchion.ifxfood.domain.repository.FormaPagamentoRepository;

@Service
public class CadastroFormaPagamentoService {

	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;
	
	public List<FormaPagamento> listar() {
		return formaPagamentoRepository.findAll();
	}

	public FormaPagamento buscar(Long id) {
		return formaPagamentoRepository.findById(id).orElseThrow(
				() -> new FormaPagamentoNaoEncontradaException(id));
	}
}
