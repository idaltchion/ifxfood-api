package com.idaltchion.ifxfood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.idaltchion.ifxfood.api.model.CozinhaDTO;
import com.idaltchion.ifxfood.domain.model.Cozinha;

@Component
public class CozinhaDTOAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public CozinhaDTO toDTO(Cozinha cozinha) {
		return modelMapper.map(cozinha, CozinhaDTO.class);
	}
	
	public List<CozinhaDTO> toDTOCollection(List<Cozinha> cozinhas) {
		return cozinhas.stream()
				.map(cozinha -> toDTO(cozinha))
				.collect(Collectors.toList());
	}
	
}
