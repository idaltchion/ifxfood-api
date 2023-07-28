package com.idaltchion.ifxfood.api.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.idaltchion.ifxfood.api.model.CidadeDTO;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel(value = "CidadesDTO")
@Data
public class CidadeDTOOpenAPI {
	private CidadesDTOEmbeddedOpenAPI _embedded;
	private Links _links;
	
	@ApiModel(value = "CidadesEmbeddedDTO")
	@Data
	private class CidadesDTOEmbeddedOpenAPI {
		private List<CidadeDTO> cidades;
	}

}
