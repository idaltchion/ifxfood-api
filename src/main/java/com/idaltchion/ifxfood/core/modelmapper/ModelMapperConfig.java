package com.idaltchion.ifxfood.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.idaltchion.ifxfood.api.v1.model.EnderecoDTO;
import com.idaltchion.ifxfood.api.v1.model.input.ItemPedidoDTOInput;
import com.idaltchion.ifxfood.api.v2.model.input.CidadeDTOInputV2;
import com.idaltchion.ifxfood.domain.model.Cidade;
import com.idaltchion.ifxfood.domain.model.Endereco;
import com.idaltchion.ifxfood.domain.model.ItemPedido;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();
		
		modelMapper.createTypeMap(ItemPedidoDTOInput.class, ItemPedido.class)
			.addMappings(mapper -> mapper.skip(ItemPedido::setId));
		
		modelMapper.createTypeMap(CidadeDTOInputV2.class, Cidade.class)
			.addMappings(mapper -> mapper.skip(Cidade::setId));
		
		var enderecoToEnderecoDTOMap = modelMapper.createTypeMap(Endereco.class, EnderecoDTO.class);
		enderecoToEnderecoDTOMap.<String>addMapping(
				fromEndereco -> fromEndereco.getCidade().getEstado().getNome(), 
				(toEnderecoDTO, valor) -> toEnderecoDTO.getCidade().setEstado(valor));
		
		return modelMapper;
	}
	
}
