package com.idaltchion.ifxfood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.idaltchion.ifxfood.api.model.CidadeDTO;
import com.idaltchion.ifxfood.domain.model.Cidade;

@Component
public class CidadeDTOAssember {

	@Autowired
	private ModelMapper modelMapper;
	
	public CidadeDTO toDTO(Cidade cidade) {
		return modelMapper.map(cidade, CidadeDTO.class);
	}
	
	public List<CidadeDTO> toDTOCollection(List<Cidade> cidades) {
		return cidades.stream()
				.map(cidade -> toDTO(cidade))
				.collect(Collectors.toList());
	}
}
