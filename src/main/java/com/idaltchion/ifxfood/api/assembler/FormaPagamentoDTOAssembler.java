package com.idaltchion.ifxfood.api.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.idaltchion.ifxfood.api.model.FormaPagamentoDTO;
import com.idaltchion.ifxfood.domain.model.FormaPagamento;

@Component
public class FormaPagamentoDTOAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public FormaPagamentoDTO toDTO(FormaPagamento formaPagamento) {
		return modelMapper.map(formaPagamento, FormaPagamentoDTO.class);
	}
	
	public List<FormaPagamentoDTO> toDTOCollection(Collection<FormaPagamento> formasPagamento) {
		return formasPagamento.stream()
				.map(formaPagamento -> toDTO(formaPagamento))
				.collect(Collectors.toList());
	}
	
}
