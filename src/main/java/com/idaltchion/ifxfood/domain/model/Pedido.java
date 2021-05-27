package com.idaltchion.ifxfood.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.idaltchion.ifxfood.domain.exception.NegocioException;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	private BigDecimal subtotal;
	private BigDecimal taxaFrete;
	private BigDecimal valorTotal;
	
	@CreationTimestamp
	private OffsetDateTime dataCriacao;
	
	private OffsetDateTime dataConfirmacao;
	private OffsetDateTime dataCancelamento;
	private OffsetDateTime dataEntrega;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private FormaPagamento formaPagamento;
	
	@Embedded
	private Endereco enderecoEntrega;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Restaurante restaurante;
	
	@Enumerated(EnumType.STRING)
	private StatusPedido status = StatusPedido.CRIADO;
	
	@ManyToOne
	@JoinColumn(nullable = false, name = "usuario_cliente_id")
	private Usuario cliente;
	
	
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	private List<ItemPedido> itens = new ArrayList<>();

	public void calcularValorTotal() {
		//1. calcula o preco total de cada item existente
		getItens().forEach(ItemPedido::calcularPrecoTotal);
		
		//2. faz o somatorio dos itens conforme seu preco total
		setSubtotal(getItens().stream()
				.map(ItemPedido::getPrecoTotal)
				.reduce(BigDecimal.ZERO, BigDecimal::add));
		
		//3. calcula o subtotal com a taxa frete
		setValorTotal(getSubtotal().add(getTaxaFrete()));
	}

	private void setStatus(StatusPedido novoStatus) {
		StatusPedido statusAtual = getStatus();
		if (statusAtual.naoPodeAlterarPara(novoStatus)) {
			throw new NegocioException(String.format("Status do pedido %d não pode ser alterado de '%s' para '%s'", 
					getId(), statusAtual.getDescricao(), novoStatus.getDescricao()));
		}
		this.status = novoStatus;
	}
	
	public void confirmar() {
		setStatus(StatusPedido.CONFIRMADO);
		setDataConfirmacao(OffsetDateTime.now());
	}

	public void entregar() {
		setStatus(StatusPedido.ENTREGUE);
		setDataEntrega(OffsetDateTime.now());
	}

	public void cancelar() {
		setStatus(StatusPedido.CANCELADO);
		setDataCancelamento(OffsetDateTime.now());
	}
	
}
