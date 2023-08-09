package com.idaltchion.ifxfood.api.v1.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.idaltchion.ifxfood.api.v1.model.UsuarioDTO;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "UsuarioDTO")
@Getter
@Setter
public class UsuarioDTOOpenAPI {
	
	private UsuariosDTOEmbeddedOpenAPI _embedded;
	private Links _links;
	
	@ApiModel(value = "UsuarioDTOEmbedded")
	@Data
	private class UsuariosDTOEmbeddedOpenAPI {
		private List<UsuarioDTO> usuarios;
	}

}
