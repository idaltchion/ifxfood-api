package com.idaltchion.ifxfood.api.assembler;

import org.springframework.stereotype.Component;

import com.idaltchion.ifxfood.api.model.input.RestauranteDTOInput;
import com.idaltchion.ifxfood.domain.model.Cozinha;
import com.idaltchion.ifxfood.domain.model.Restaurante;

@Component
public class RestauranteDTODisassembler {

	/*
	 * efetua a conversao do objeto de Modelo de Representacao (DTO de Entrada) para Modelo de Dominio
	 */
	public Restaurante toDomain(RestauranteDTOInput restauranteInput) {
		Restaurante restaurante = new Restaurante();
		restaurante.setNome(restauranteInput.getNome());
		restaurante.setTaxaFrete(restauranteInput.getTaxaFrete());
		
		Cozinha cozinha = new Cozinha();
		cozinha.setId(restauranteInput.getCozinha().getId());
		
		restaurante.setCozinha(cozinha);
		
		return restaurante;
	}
	
}
