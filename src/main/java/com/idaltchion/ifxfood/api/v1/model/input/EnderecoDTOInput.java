package com.idaltchion.ifxfood.api.v1.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDTOInput {

	@ApiModelProperty(example = "20123-090", required = true)
	@NotBlank
	private String cep;
	
	@ApiModelProperty(example = "Rua dos papagaios", required = true)
	@NotBlank
	private String logradouro;
	
	@ApiModelProperty(example = "34", required = true)
	@NotBlank
	private String numero;
	
	@ApiModelProperty(example = "ap 10")
	private String complemento;
	
	@ApiModelProperty(example = "Jardim das Aves", required = true)
	@NotBlank
	private String bairro;
	
	@NotNull
	@Valid
	private CidadeIdDTOInput cidade;
	
}
