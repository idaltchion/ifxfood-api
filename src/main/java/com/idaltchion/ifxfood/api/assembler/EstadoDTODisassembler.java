package com.idaltchion.ifxfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.idaltchion.ifxfood.api.model.input.EstadoDTOInput;
import com.idaltchion.ifxfood.domain.model.Estado;

/*
 * Classe responsavel por transformar um objeto de Modelo de Representacao para um objeto de Modelo de Dominio
 */
@Component
public class EstadoDTODisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Estado toDomainObject(EstadoDTOInput estadoInput) {
		return modelMapper.map(estadoInput, Estado.class);
	}

	public void copyToDomainObject(EstadoDTOInput estadoInput, Estado estadoAtual) {
		modelMapper.map(estadoInput, estadoAtual);
	}
	
}
