package com.idaltchion.ifxfood.api.v1.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.idaltchion.ifxfood.api.v1.model.PedidoResumoDTO;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@ApiModel("PedidoResumoDTO")
@Getter
@Setter
public class PedidoResumoDTOOpenAPI {
	private PedidosResumoDTOEmbeddedOpenAPI _embedded;
	private Links _links;
	private PageModelOpenAPI page;
	
	@ApiModel(value = "PedidosResumoDTOEmbedded")
	@Data
	private class PedidosResumoDTOEmbeddedOpenAPI {
		private List<PedidoResumoDTO> pedidosResumo;
	}

}
