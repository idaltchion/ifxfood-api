package com.idaltchion.ifxfood.api.v1.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.idaltchion.ifxfood.api.v1.model.EstadoDTO;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "EstadoDTO")
@Getter
@Setter
public class EstadoDTOOpenAPI {
	private EstadosDTOEmbeddedOpenAPI _embedded;
	private Links _links;
	
	@Data
	@ApiModel(value = "EstadosEmbeddedDTO")
	private class EstadosDTOEmbeddedOpenAPI {
		private List<EstadoDTO> estados;
	}
	
}
