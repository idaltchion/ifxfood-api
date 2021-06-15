package com.idaltchion.ifxfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.idaltchion.ifxfood.api.model.FotoProdutoDTO;
import com.idaltchion.ifxfood.domain.model.FotoProduto;

@Component
public class FotoProdutoDTOAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public FotoProdutoDTO toDTO(FotoProduto foto) {
		return modelMapper.map(foto, FotoProdutoDTO.class);
	}

}
