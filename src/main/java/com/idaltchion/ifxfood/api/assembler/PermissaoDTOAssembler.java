package com.idaltchion.ifxfood.api.assembler;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.idaltchion.ifxfood.domain.model.Permissao;
import com.idaltchion.ifxfood.domain.model.PermissaoDTO;

@Component
public class PermissaoDTOAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public PermissaoDTO toDTO(Permissao permissao) {
		return modelMapper.map(permissao, PermissaoDTO.class);
	}
	
	public List<PermissaoDTO> toCollectionModel(Set<Permissao> permissoes) {
		return permissoes.stream()
				.map(permissao -> toDTO(permissao))
				.collect(Collectors.toList());
	}
	
}