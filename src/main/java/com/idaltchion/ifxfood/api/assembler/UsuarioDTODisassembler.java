package com.idaltchion.ifxfood.api.assembler;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.idaltchion.ifxfood.api.model.input.UsuarioComSenhaDTOInput;
import com.idaltchion.ifxfood.api.model.input.UsuarioDTOInput;
import com.idaltchion.ifxfood.domain.model.Usuario;

@Component
public class UsuarioDTODisassembler {

	@Autowired
	ModelMapper modelMapper;
	
	public Usuario toModelObject(UsuarioComSenhaDTOInput usuarioInput) {
		return modelMapper.map(usuarioInput, Usuario.class);
	}

	public void copyToModelObject(@Valid UsuarioDTOInput usuarioInput, Usuario usuarioAtual) {
		modelMapper.map(usuarioInput, usuarioAtual);
	}
	
}
