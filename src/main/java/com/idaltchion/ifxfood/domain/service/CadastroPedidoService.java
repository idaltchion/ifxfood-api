package com.idaltchion.ifxfood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.idaltchion.ifxfood.api.model.input.filter.PedidoFilter;
import com.idaltchion.ifxfood.domain.exception.NegocioException;
import com.idaltchion.ifxfood.domain.exception.PedidoNaoEncontradoException;
import com.idaltchion.ifxfood.domain.model.Cidade;
import com.idaltchion.ifxfood.domain.model.FormaPagamento;
import com.idaltchion.ifxfood.domain.model.ItemPedido;
import com.idaltchion.ifxfood.domain.model.Pedido;
import com.idaltchion.ifxfood.domain.model.Produto;
import com.idaltchion.ifxfood.domain.model.Restaurante;
import com.idaltchion.ifxfood.domain.model.Usuario;
import com.idaltchion.ifxfood.domain.repository.PedidoRepository;
import com.idaltchion.ifxfood.infrastructure.repository.spec.PedidoSpecs;

@Service
public class CadastroPedidoService {

	@Autowired
	PedidoRepository pedidoRepository;
	
	@Autowired
	CadastroRestauranteService restauranteService;
	
	@Autowired
	CadastroFormaPagamentoService formaPagamentoService;
	
	@Autowired
	CadastroProdutoService produtoService;
	
	@Autowired
	CadastroCidadeService cidadeService;
	
	@Autowired
	CadastroUsuarioService usuarioService;
	
	public List<Pedido> listar(PedidoFilter filtro) {
		return pedidoRepository.findAll(PedidoSpecs.usandoFiltro(filtro));
	}

	public Pedido buscar(String codigo_pedido) {
		return pedidoRepository.findByCodigo(codigo_pedido).orElseThrow(
				() -> new PedidoNaoEncontradoException(codigo_pedido));
	}

	@Transactional
	public Pedido emitirPedido(Pedido pedido) {
		validarPedido(pedido);
		validarItens(pedido);
		
		pedido.calcularValorTotal();
		
		return pedidoRepository.save(pedido);
	}

	private void validarPedido(Pedido pedido) {
		Usuario cliente = usuarioService.buscar(pedido.getCliente().getId());
		Restaurante restaurante = restauranteService.buscar(pedido.getRestaurante().getId());
		Cidade cidade = cidadeService.buscar(pedido.getEnderecoEntrega().getCidade().getId());
		FormaPagamento formaPagamentoUtilizada = formaPagamentoService.buscar(pedido.getFormaPagamento().getId()); 
		
		if (!restaurante.aceitaFormaPagamento(formaPagamentoUtilizada)) {
			throw new NegocioException(
					String.format("A forma de pagamento '%s' não é aceita pelo restaurante %d", 
							formaPagamentoUtilizada.getDescricao(), 
							restaurante.getId()));
		}
		
		pedido.setCliente(cliente);
		pedido.setRestaurante(restaurante);
		pedido.setTaxaFrete(restaurante.getTaxaFrete());
		pedido.getEnderecoEntrega().setCidade(cidade);
		pedido.setFormaPagamento(formaPagamentoUtilizada);
	}

	private void validarItens(Pedido pedido) {
		List<ItemPedido> itens = pedido.getItens();
		itens.forEach(item -> {
			Produto produto = produtoService.buscar(item.getProduto().getId(), pedido.getRestaurante().getId());
			item.setPrecoUnitario(produto.getPreco());
			item.setProduto(produto);
			item.setPedido(pedido);
		});
	}
	
}
