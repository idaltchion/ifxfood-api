package com.idaltchion.ifxfood.api;

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
import org.springframework.hateoas.TemplateVariable.VariableType;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.idaltchion.ifxfood.api.controller.CidadeController;
import com.idaltchion.ifxfood.api.controller.CozinhaController;
import com.idaltchion.ifxfood.api.controller.EstadoController;
import com.idaltchion.ifxfood.api.controller.FluxoPedidoController;
import com.idaltchion.ifxfood.api.controller.FormaPagamentoController;
import com.idaltchion.ifxfood.api.controller.PedidoController;
import com.idaltchion.ifxfood.api.controller.RestauranteController;
import com.idaltchion.ifxfood.api.controller.RestauranteFormaPagamentoController;
import com.idaltchion.ifxfood.api.controller.RestauranteProdutoController;
import com.idaltchion.ifxfood.api.controller.RestauranteUsuarioResponsavelController;
import com.idaltchion.ifxfood.api.controller.UsuarioController;
import com.idaltchion.ifxfood.api.controller.UsuarioGrupoController;

@Component
public class IfxLinks {

	public static final TemplateVariables PAGE_VARIABLES = new TemplateVariables(
			new TemplateVariable("page", VariableType.REQUEST_PARAM),
			new TemplateVariable("size", VariableType.REQUEST_PARAM),
			new TemplateVariable("sort", VariableType.REQUEST_PARAM));
	
	public Link linkToPedidos() {
		String pedidosUrl = WebMvcLinkBuilder.linkTo(PedidoController.class).toUri().toString();
		
		TemplateVariables filterVariables = new TemplateVariables(
				new TemplateVariable("clienteId", VariableType.REQUEST_PARAM),
				new TemplateVariable("restauranteId", VariableType.REQUEST_PARAM),
				new TemplateVariable("dataCriacaoInicio", VariableType.REQUEST_PARAM),
				new TemplateVariable("dataCriacaoFim", VariableType.REQUEST_PARAM));
		
		return Link.of(UriTemplate.of(pedidosUrl, filterVariables.concat(PAGE_VARIABLES)), IanaLinkRelations.COLLECTION);
	}
	
	public Link linkToConfirmacaoPedido(String codigo_pedido) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(FluxoPedidoController.class).confirmacao(codigo_pedido)).withRel("confirmar");
	}

	public Link linkToEntregaPedido(String codigo_pedido) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(FluxoPedidoController.class).entrega(codigo_pedido)).withRel("entregar");
	}

	public Link linkToCancelamentoPedido(String codigo_pedido) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(FluxoPedidoController.class).cancelamento(codigo_pedido)).withRel("cancelar");
	}
	
	public Link linkToCidade(Long cidadeId) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CidadeController.class).buscar(cidadeId)).withSelfRel();
	}
	
	public Link linkToCidades(String rel) {
		return WebMvcLinkBuilder.linkTo(CidadeController.class).withRel(rel);
	}
	
	public Link linkToCidades() {
		return linkToCidades(IanaLinkRelations.SELF.value());
	}
	
	public Link linkToEstados() {
		return WebMvcLinkBuilder.linkTo(EstadoController.class).withRel(IanaLinkRelations.COLLECTION);
	}
	
	public Link linkToEstado(Long estadoId) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EstadoController.class).buscar(estadoId)).withSelfRel();
	}

	public Link linkToCozinhas() {
		return WebMvcLinkBuilder.linkTo(CozinhaController.class).withRel(IanaLinkRelations.COLLECTION);
	}
	
	public Link linkToCozinha(Long cozinhaId) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CozinhaController.class).buscar(cozinhaId)).withSelfRel();
	}

	public Link linkToFormaPagamento(Long formaPagamentoId) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(FormaPagamentoController.class).buscar(formaPagamentoId)).withSelfRel();
	}

	public Link linkToRestaurantes(String rel) {
		String restauranteUrl = WebMvcLinkBuilder.linkTo(RestauranteController.class).toUri().toString();
		TemplateVariables templateVariables = new TemplateVariables(
				new TemplateVariable("projecao", VariableType.REQUEST_PARAM));
		
		return Link.of(UriTemplate.of(restauranteUrl, templateVariables), rel);
	}
	
	public Link linkToRestaurante(Long restauranteId) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestauranteController.class).buscar(restauranteId)).withSelfRel();
	}

	public Link linkToUsuarios() {
		return WebMvcLinkBuilder.linkTo(UsuarioController.class).withRel("usuarios");
	}
	
	public Link linkToUsuario(Long usuarioId) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).buscar(usuarioId)).withSelfRel();
	}

	public Link linkToGruposUsuario(Long usuarioId) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioGrupoController.class).listar(usuarioId)).withRel("grupos-usuario");
	}
	
	public Link linkToProduto(Long restauranteId, Long produtoId) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestauranteProdutoController.class)
				.buscar(restauranteId, produtoId))
				.withSelfRel();
	}

	public Link linkToFormasPagamentoRestaurante(Long restauranteId) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestauranteFormaPagamentoController.class).listar(restauranteId)).withRel("formas-pagamento");
	}

	public Link linkToResponsaveisRestaurante(Long restauranteId) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestauranteUsuarioResponsavelController.class).listar(restauranteId)).withRel("responsaveis");
	}

	public Link linkToInativarRestaurante(Long restauranteId) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestauranteController.class).inativar(restauranteId)).withRel("inativar");
	}

	public Link linkToAtivarRestaurante(Long restauranteId) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestauranteController.class).ativar(restauranteId)).withRel("ativar");
	}

	public Link linkToFecharRestaurante(Long restauranteId) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestauranteController.class).fechar(restauranteId)).withRel("fechar");
	}

	public Link linkToAbrirRestaurante(Long restauranteId) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestauranteController.class).abrir(restauranteId)).withRel("abrir");
	}
	
}
