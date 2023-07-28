package com.idaltchion.ifxfood.api.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.idaltchion.ifxfood.api.model.GrupoDTO;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "GrupoDTO")
@Getter
@Setter
public class GrupoDTOOpenAPI {
	private GruposDTOEmbeddedOpenAPI _embedded;
	private Links _links;
	
	@ApiModel(value = "GrupoDTOEmbedded")
	@Data
	private class GruposDTOEmbeddedOpenAPI {
		private List<GrupoDTO> grupos;
	}
	
}
