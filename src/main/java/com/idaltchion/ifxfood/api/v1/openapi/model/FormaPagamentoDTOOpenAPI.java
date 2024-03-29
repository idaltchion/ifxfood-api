package com.idaltchion.ifxfood.api.v1.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.idaltchion.ifxfood.api.v1.model.FormaPagamentoDTO;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "FormaPagamentoDTO")
@Getter
@Setter
public class FormaPagamentoDTOOpenAPI {
	private FormasPagamentoEmbeddedDTOOpenAPI _embedded;
	private Links _links; 
	
	@ApiModel(value = "FormaPagamentoEmbeddedDTO")
	@Data
	private class FormasPagamentoEmbeddedDTOOpenAPI {
		private List<FormaPagamentoDTO> formasPagamento;
	}
	
}
