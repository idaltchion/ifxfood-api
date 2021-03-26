package com.idaltchion.ifxfood.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.idaltchion.ifxfood.api.model.EnderecoDTO;
import com.idaltchion.ifxfood.domain.model.Endereco;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();
		
		var enderecoToEnderecoDTOMap = modelMapper.createTypeMap(Endereco.class, EnderecoDTO.class);
		enderecoToEnderecoDTOMap.<String>addMapping(
				fromEndereco -> fromEndereco.getCidade().getEstado().getNome(), 
				(toEnderecoDTO, valor) -> toEnderecoDTO.getCidade().setEstado(valor));
		
		return modelMapper;
	}
	
}
