package com.idaltchion.ifxfood.api.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.idaltchion.ifxfood.api.model.GrupoDTO;
import com.idaltchion.ifxfood.domain.model.Grupo;

@Component
public class GrupoDTOAssembler {

	@Autowired
	ModelMapper modelMapper;
	
	public GrupoDTO toDTO(Grupo grupo) {
		return modelMapper.map(grupo, GrupoDTO.class);
	}
	
	public List<GrupoDTO> toCollectionDTO(Collection<Grupo> grupos) {
		return grupos.stream()
				.map(grupo -> toDTO(grupo))
				.collect(Collectors.toList());
	}

}
