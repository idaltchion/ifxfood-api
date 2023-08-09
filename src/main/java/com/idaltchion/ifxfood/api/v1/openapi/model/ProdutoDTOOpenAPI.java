package com.idaltchion.ifxfood.api.v1.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.idaltchion.ifxfood.api.v1.model.ProdutoDTO;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "ProdutoDTO")
@Getter
@Setter
public class ProdutoDTOOpenAPI {

	private ProdutosDTOEmbeddedOpenAPI _embedded;
	private Links _links;
	
	@ApiModel(value = "ProdutosDTOEmbedded")
	@Data
	private class ProdutosDTOEmbeddedOpenAPI {
		private List<ProdutoDTO> produtos;
	}
	
}
