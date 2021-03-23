package com.idaltchion.ifxfood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idaltchion.ifxfood.api.assembler.FormaPagamentoDTOAssembler;
import com.idaltchion.ifxfood.api.model.FormaPagamentoDTO;
import com.idaltchion.ifxfood.domain.model.FormaPagamento;
import com.idaltchion.ifxfood.domain.service.CadastroFormaPagamentoService;

@RestController
@RequestMapping("/formas-pagamento")
public class FormaPagamentoController {

	@Autowired
	private CadastroFormaPagamentoService formaPagamentoService;
	
	@Autowired
	private FormaPagamentoDTOAssembler formaPagamentoDTOAssembler;
	
	@GetMapping
	public List<FormaPagamentoDTO> listar() {
		return formaPagamentoDTOAssembler.toDTOCollection(formaPagamentoService.listar());
	}
	
	@GetMapping("/{id}")
	public FormaPagamentoDTO buscar(@PathVariable Long id) {
		FormaPagamento formaPagamento = formaPagamentoService.buscar(id);
		return formaPagamentoDTOAssembler.toDTO(formaPagamento);
	}
	
}
