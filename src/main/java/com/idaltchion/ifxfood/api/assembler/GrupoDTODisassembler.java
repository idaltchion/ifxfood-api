package com.idaltchion.ifxfood.api.assembler;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.idaltchion.ifxfood.api.model.input.GrupoDTOInput;
import com.idaltchion.ifxfood.domain.model.Grupo;

@Component
public class GrupoDTODisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Grupo toDomainObject(@Valid GrupoDTOInput grupoInput) {
		return modelMapper.map(grupoInput, Grupo.class);
	}

	public void copyToDomainObject(@Valid GrupoDTOInput grupoInput, Grupo grupoAtual) {
		modelMapper.map(grupoInput, grupoAtual);
	}
	
}
