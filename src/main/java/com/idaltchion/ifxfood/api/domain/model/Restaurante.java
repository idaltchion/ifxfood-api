package com.idaltchion.ifxfood.api.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
//	o nullable se aplica somente ao BANCO DE DADOS no momento da criacao da tabela de forma automatica
//	a anotacao @NotNull se aplica somente na validacao pela APLICACAO
	@Column(nullable = false)
	private String nome;
	
	@Column(name = "taxa_frete", nullable = false)
	private BigDecimal taxaFrete;
	
	@ManyToOne
	@JoinColumn(name = "cozinha_id", nullable = false)
//	caso deseja explicitar o nome da coluna que sera referenciada na FK. O padrao e <object>_id, portanto, cozinha_id
//	se for quere utilizar o nome padrao, essa anotacao pode ser removida. Nesse caso esta sendo usada comente devido ao nullable
	private Cozinha cozinha;

}
