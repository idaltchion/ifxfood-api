package com.idaltchion.ifxfood.api.v2.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.idaltchion.ifxfood.api.v2.model.input.EstadoDTOInputV2;
import com.idaltchion.ifxfood.domain.model.Estado;

/*
 * Classe responsavel por transformar um objeto de Modelo de Representacao para um objeto de Modelo de Dominio
 */
@Component
public class EstadoDTODisassemblerV2 {

	@Autowired
	private ModelMapper modelMapper;
	
	public Estado toDomainObject(EstadoDTOInputV2 estadoInput) {
		return modelMapper.map(estadoInput, Estado.class);
	}

	public void copyToDomainObject(EstadoDTOInputV2 estadoInput, Estado estadoAtual) {
		modelMapper.map(estadoInput, estadoAtual);
	}
	
}
