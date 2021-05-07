package com.idaltchion.ifxfood.api.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.idaltchion.ifxfood.api.model.UsuarioDTO;
import com.idaltchion.ifxfood.domain.model.Usuario;

@Component
public class UsuarioDTOAssembler {

	@Autowired
	ModelMapper modelMapper;

	public UsuarioDTO toDTO(Usuario usuario) {
		return modelMapper.map(usuario, UsuarioDTO.class);
	}

	public List<UsuarioDTO> toDTOCollection(Collection<Usuario> usuarios) {
		return usuarios.stream()
				.map(usuario -> toDTO(usuario))
				.collect(Collectors.toList());
	}

}
