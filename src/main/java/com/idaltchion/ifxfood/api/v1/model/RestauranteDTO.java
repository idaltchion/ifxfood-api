package com.idaltchion.ifxfood.api.v1.model;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "restaurantes")
@Getter
@Setter
public class RestauranteDTO extends RepresentationModel<RestauranteDTO> {

	@ApiModelProperty(example = "2")
	private Long id;
	
	@ApiModelProperty(example = "Restaurante Madalozo")
	private String nome;
	
	@ApiModelProperty(example = "4.50")
	private BigDecimal taxaFrete;
	
	private CozinhaDTO cozinha;
	private Boolean ativo;
	private Boolean aberto;
	private EnderecoDTO endereco;
	
}
