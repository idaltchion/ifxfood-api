/*
 * Attributes based on RFC 7807 - Problem Details for HTTP APIs 
 */
package com.idaltchion.ifxfood.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@ApiModel(description = "Representação de um problema")
@JsonInclude(Include.NON_NULL)
@Getter
@Builder
public class Problem {
	
	@ApiModelProperty(position = 5)
	private OffsetDateTime timestamp;
	
	@ApiModelProperty(example = "400", position = 10)
	private Integer status;
	
	@ApiModelProperty(example = "https://ifxfood.com.br/dado-invalido", position = 15)
	private String type;
	
	@ApiModelProperty(example = "Dado inválido", position = 20)
	private String title;
	
	@ApiModelProperty(example = "Um ou mais campos estão inválidos. Preencha corretamente e tente novamente.", position = 25)
	private String detail;
	
	@ApiModelProperty(example = "Um ou mais campos estão inválidos. Preencha corretamente e tente novamente.", position = 30)
	private String userMessage;
	
	@ApiModelProperty(position = 35)
	private List<Field> fields;
	
	@ApiModel(description = "Representação de campo com problema (opcional)")
	@Getter
	@Builder
	public static class Field {
		
		@ApiModelProperty(example = "preco")
		private String name;
		
		@ApiModelProperty(example = "O preenchimento do campo preco é obrigatório")
		private String userMessage;
	}
}
