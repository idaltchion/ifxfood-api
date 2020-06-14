package com.idaltchion.ifxfood.api.domain.repository;

import java.util.List;

import com.idaltchion.ifxfood.api.domain.model.FormaPagamento;

public interface FormaPagamentoRepository {

	public List<FormaPagamento> listar();
	public FormaPagamento buscar(Long id);
	public FormaPagamento salvar(FormaPagamento formaPagamento);
	public void remover(FormaPagamento formaPagamento);
	
}
