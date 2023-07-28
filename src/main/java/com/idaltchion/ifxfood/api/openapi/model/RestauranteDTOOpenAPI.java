package com.idaltchion.ifxfood.api.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.idaltchion.ifxfood.api.model.RestauranteTaxaFreteDTO;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "RestauranteDTO")
@Getter
@Setter
public class RestauranteDTOOpenAPI {

	private RestaurantesDTOEmbeddedOpenAPI _embedded;
	private Links _links;
	
	@ApiModel(value = "RestaurantesDTOEmbedded")
	@Data
	private class RestaurantesDTOEmbeddedOpenAPI {
		private List<RestauranteTaxaFreteDTO> restaurantes; 
	}
	
}
