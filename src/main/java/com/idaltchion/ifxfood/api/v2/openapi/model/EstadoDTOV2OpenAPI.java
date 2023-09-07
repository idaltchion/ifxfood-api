package com.idaltchion.ifxfood.api.v2.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.idaltchion.ifxfood.api.v2.model.EstadoDTOV2;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "EstadoDTO")
@Getter
@Setter
public class EstadoDTOV2OpenAPI {

	private EstadosDTOEmbeddedOpenAPI _embedded;
	private Links _links;
	
	@ApiModel(value = "EstadosEmbeddedDTO")
	@Data
	private class EstadosDTOEmbeddedOpenAPI {
		private List<EstadoDTOV2> estados;
	}
}
