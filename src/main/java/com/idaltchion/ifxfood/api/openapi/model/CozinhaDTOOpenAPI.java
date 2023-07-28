package com.idaltchion.ifxfood.api.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.idaltchion.ifxfood.api.model.CozinhaDTO;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "CozinhasDTO")
@Getter
@Setter
public class CozinhaDTOOpenAPI {
	private CozinhasDTOEmbeddedOpenAPI _embedded;
	private Links _links;
	private PageModelOpenAPI page;
	
	@ApiModel(value = "CozinhasEmbeddedDTO")
	@Data
	private class CozinhasDTOEmbeddedOpenAPI {
		private List<CozinhaDTO> cozinhas;
		
		
		
	}
	
}
