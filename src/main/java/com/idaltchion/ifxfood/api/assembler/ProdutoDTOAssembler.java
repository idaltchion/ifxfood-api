package com.idaltchion.ifxfood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.idaltchion.ifxfood.api.model.ProdutoDTO;
import com.idaltchion.ifxfood.domain.model.Produto;

@Component
public class ProdutoDTOAssembler {

	@Autowired
	ModelMapper modelMapper;

	public List<ProdutoDTO> toDTOCollection(List<Produto> produtos) {
		return produtos.stream()
				.map(produto -> toDTO(produto))
				.collect(Collectors.toList());
	}

	public ProdutoDTO toDTO(Produto produto) {
		return modelMapper.map(produto, ProdutoDTO.class);
	}
	
}
