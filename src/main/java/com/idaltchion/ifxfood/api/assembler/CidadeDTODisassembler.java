package com.idaltchion.ifxfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.idaltchion.ifxfood.api.model.input.CidadeDTOInput;
import com.idaltchion.ifxfood.domain.model.Cidade;
import com.idaltchion.ifxfood.domain.model.Estado;

@Component
public class CidadeDTODisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Cidade toDomainObject(CidadeDTOInput cidadeDTOInput) {
		return modelMapper.map(cidadeDTOInput, Cidade.class);
	}
	
	public void copyToDomainObject(CidadeDTOInput cidadeDTOInput, Cidade cidadeAtual) {
		/*
		 * Para evitar a exception:
		 * Caused by: org.hibernate.HibernateException: 
		 * identifier of an instance of com.idaltchion.ifxfood.domain.model.Estado was altered from 1 to 6
		 */
		cidadeAtual.setEstado(new Estado());
		modelMapper.map(cidadeDTOInput, cidadeAtual);
	}
	
}
