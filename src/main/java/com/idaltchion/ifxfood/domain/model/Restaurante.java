package com.idaltchion.ifxfood.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
	
	/*
	 * o nullable se aplica somente ao BANCO DE DADOS no momento da criacao da tabela de forma automatica
	 * a anotacao @NotNull se aplica somente na validacao pela APLICACAO
	 * @NotBlank: e outras validacaoes das classes de dominio foram movidas para a classe de DTO de Entrada, que agora faz as validacoes
	 * 
	 */
	@Column(nullable = false)
	private String nome;
	
	@Column(name = "taxa_frete", nullable = false)
	private BigDecimal taxaFrete;

	/*
	 * @JoinColumn
	 * caso desejar explicitar o nome da coluna que sera referenciada na FK. O padrao e <object>_id, portanto, cozinha_id
	 * se for querer utilizar o nome padrao, essa anotacao pode ser removida. Nesse caso esta sendo usada somente devido ao nullable
	 */
	@JoinColumn(name = "cozinha_id", nullable = false)
	@ManyToOne
	private Cozinha cozinha;

	@ManyToMany
	@JoinTable(name = "restaurante_forma_pagamento",
			joinColumns = @JoinColumn(name = "restaurante_id"), 
			inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
	private Set<FormaPagamento> formasPagamento = new HashSet<>();
	
	@Embedded
	private Endereco endereco;
	
	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private OffsetDateTime dataCadastro;
	
	@UpdateTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private OffsetDateTime dataAtualizacao;
	
	@OneToMany(mappedBy = "restaurante")
	private List<Produto> produtos = new ArrayList<>();

	private Boolean ativo = Boolean.TRUE;
	
	public void ativar() {
		setAtivo(true);
	}
	
	public void inativar() {
		setAtivo(false);
	}
	
	public boolean adicionarFormaPagamento(FormaPagamento formaPagamento) {
		return formasPagamento.add(formaPagamento);
	}

	public boolean removerFormaPagamento(FormaPagamento formaPagamento) {
		return formasPagamento.remove(formaPagamento);
	}
	
}
