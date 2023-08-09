package com.idaltchion.ifxfood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.idaltchion.ifxfood.api.v1.model.input.FormaPagamentoDTOInput;
import com.idaltchion.ifxfood.domain.model.FormaPagamento;

@Component
public class FormaPagamentoDTODisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public FormaPagamento toModelObject(FormaPagamentoDTOInput formaPagamentoInput) {
		return modelMapper.map(formaPagamentoInput, FormaPagamento.class);
	}

	public void copyToDomainObject(FormaPagamentoDTOInput formaPagamentoInput, FormaPagamento formaPagamento) {
		modelMapper.map(formaPagamentoInput, formaPagamento);
	}
	
}
