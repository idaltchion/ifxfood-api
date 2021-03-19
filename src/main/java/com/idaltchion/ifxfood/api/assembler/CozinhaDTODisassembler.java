package com.idaltchion.ifxfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.idaltchion.ifxfood.api.model.input.CozinhaDTOInput;
import com.idaltchion.ifxfood.domain.model.Cozinha;

@Component
public class CozinhaDTODisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Cozinha toDomainObject(CozinhaDTOInput cozinhaInput) {
		return modelMapper.map(cozinhaInput, Cozinha.class);
	}

	public void copyToDomainObject(CozinhaDTOInput cozinhaInput, Cozinha cozinhaAtual) {
		modelMapper.map(cozinhaInput, cozinhaAtual);		
	}
	
}
