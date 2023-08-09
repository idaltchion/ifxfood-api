package com.idaltchion.ifxfood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.idaltchion.ifxfood.api.v1.model.input.RestauranteDTOInput;
import com.idaltchion.ifxfood.domain.model.Cidade;
import com.idaltchion.ifxfood.domain.model.Cozinha;
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
	
	public void copyToDomainObject(RestauranteDTOInput restauranteInput, Restaurante restaurante) {
		/* 
		 * Nova instância de cozinha adicionada para evitar a exception:
		 * Caused by: org.hibernate.HibernateException: identifier of an instance of com.idaltchion.ifxfood.domain.model.Cozinha was altered from 3 to 1
		 */
		restaurante.setCozinha(new Cozinha());
		restaurante.getEndereco().setCidade(new Cidade());
		
		modelMapper.map(restauranteInput, restaurante);
	}
	
}
