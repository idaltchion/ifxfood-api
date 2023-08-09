package com.idaltchion.ifxfood.api.v1.model;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FotoProdutoDTO extends RepresentationModel<FotoProdutoDTO>{

	private String nomeArquivo;
	private String descricao;
	private String contentType;
	private Long tamanho;
	
}
