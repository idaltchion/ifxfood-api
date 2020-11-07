package com.idaltchion.ifxfood.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.idaltchion.ifxfood.core.validation.Groups;

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
	@NotBlank
	@Column(nullable = false)
	private String nome;
	
	@PositiveOrZero
	@NotNull
	@Column(name = "taxa_frete", nullable = false)
	private BigDecimal taxaFrete;

//	essa propriedade existe somente na classe "proxy" (criada em tempo de execucao) e, foi adicionada apos mudar o fetch para LAZY
	@JsonIgnoreProperties("hibernateLazyInitializer")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cozinha_id", nullable = false)
//	caso desejar explicitar o nome da coluna que sera referenciada na FK. O padrao e <object>_id, portanto, cozinha_id
//	se for querer utilizar o nome padrao, essa anotacao pode ser removida. Nesse caso esta sendo usada somente devido ao nullable
	@Valid
	@NotNull
	@ConvertGroup(from = Default.class, to = Groups.CozinhaId.class)
	private Cozinha cozinha;

	//para nao mostrar no payload da lista de restaurantes
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "restaurante_forma_pagamento",
			joinColumns = @JoinColumn(name = "restaurante_id"), 
			inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
	private List<FormaPagamento> formasPagamento = new ArrayList<>();
	
	@JsonIgnore
	@Embedded
	private Endereco endereco;
	
	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private LocalDateTime dataCadastro;
	
	@UpdateTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private LocalDateTime dataAtualizacao;
	
	@OneToMany(mappedBy = "restaurante")
	@JsonIgnore //para evitar problema de referencia circular, pois o relacionamento e bi-direcional
	private List<Produto> produtos = new ArrayList<>();
	
}