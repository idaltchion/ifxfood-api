package com.idaltchion.ifxfood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.idaltchion.ifxfood.api.model.EstadoDTO;
import com.idaltchion.ifxfood.domain.model.Estado;

/*
 * Classe responsavel por transformar um objeto do tipo Modelo de Dominio para objeto de Modelo de Representacao 
 */
@Component
public class EstadoDTOAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public EstadoDTO toDTO(Estado estado) {
		return modelMapper.map(estado, EstadoDTO.class);
	}
	
	public List<EstadoDTO> toCollectionDTO(List<Estado> estados) {
		return estados.stream()
				.map(estado -> toDTO(estado))
				.collect(Collectors.toList());
	}
	
}
