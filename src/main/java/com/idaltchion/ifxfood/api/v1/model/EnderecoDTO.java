package com.idaltchion.ifxfood.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDTO {

	@ApiModelProperty(example = "20123-090")
	private String cep;
	
	@ApiModelProperty(example = "Rua dos papagaios")
	private String logradouro;
	
	@ApiModelProperty(example = "34")
	private String numero;
	
	@ApiModelProperty(example = "ap 19")
	private String complemento;
	
	@ApiModelProperty(example = "Jardim das Aves")
	private String bairro;
	
	private CidadeResumoDTO cidade;
	
}
