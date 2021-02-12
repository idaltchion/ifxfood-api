package com.idaltchion.ifxfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.idaltchion.ifxfood.api.model.input.RestauranteDTOInput;
import com.idaltchion.ifxfood.domain.model.Restaurante;

@Component
public class RestauranteDTODisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	/*
	 * efetua a conversao do objeto de Modelo de Representacao (DTO de Entrada) para Modelo de Dominio
	 */
	public Restaurante toDomain(RestauranteDTOInput restauranteInput) {
		return modelMapper.map(restauranteInput, Restaurante.class);
	}
	
}
