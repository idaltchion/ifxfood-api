package com.idaltchion.ifxfood.api.v1.assembler;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.idaltchion.ifxfood.api.v1.model.input.ProdutoDTOInput;
import com.idaltchion.ifxfood.domain.model.Produto;

@Component
public class ProdutoDTODisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Produto toObjectModel(@Valid ProdutoDTOInput produtoInput) {
		return modelMapper.map(produtoInput, Produto.class);
	}

	public void copyToDomainObject(@Valid ProdutoDTOInput produtoInput, Produto produto) {
		modelMapper.map(produtoInput, produto);
	}
	
}
