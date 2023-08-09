package com.idaltchion.ifxfood.api.v2.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.idaltchion.ifxfood.api.v2.model.input.CidadeDTOInputV2;
import com.idaltchion.ifxfood.domain.model.Cidade;
import com.idaltchion.ifxfood.domain.model.Estado;

@Component
public class CidadeDTODisassemblerV2 {

	@Autowired
	private ModelMapper modelMapper;
	
	public Cidade toDomainObject(CidadeDTOInputV2 cidadeDTOInput) {
		return modelMapper.map(cidadeDTOInput, Cidade.class);
	}
	
	public void copyToDomainObject(CidadeDTOInputV2 cidadeDTOInput, Cidade cidadeAtual) {
		/*
		 * Para evitar a exception:
		 * Caused by: org.hibernate.HibernateException: 
		 * identifier of an instance of com.idaltchion.ifxfood.domain.model.Estado was altered from 1 to 6
		 */
		cidadeAtual.setEstado(new Estado());
		modelMapper.map(cidadeDTOInput, cidadeAtual);
	}
	
}
