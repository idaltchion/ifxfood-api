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
import com.idaltchion.ifxfood.api.controller.GrupoController;
import com.idaltchion.ifxfood.api.controller.GrupoPermissaoController;
import com.idaltchion.ifxfood.api.controller.PedidoController;
import com.idaltchion.ifxfood.api.controller.PermissaoController;
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

	public Link linkToGrupos(String rel) {
		return WebMvcLinkBuilder.linkTo(GrupoController.class).withRel(rel);
	}
	
	public Link linkToGruposUsuario(Long usuarioId) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioGrupoController.class).listar(usuarioId)).withRel("grupos-usuario");
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

	public Link linkToFormasPagamento(String rel) {
		return WebMvcLinkBuilder.linkTo(FormaPagamentoController.class).withRel(rel);
	}

	public Link linktoDesassociarFormaPagamentoRestaurante(Long formaPagamentoId, Long restauranteId, String rel) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestauranteFormaPagamentoController.class).desassociar(formaPagamentoId, restauranteId)).withRel(rel);
	}

	public Link linktoAssociarFormaPagamentoRestaurante(Long restauranteId, String rel) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestauranteFormaPagamentoController.class).associar(null, restauranteId)).withRel(rel);
	}
	
	public Link linkToFormaPagamento(Long formaPagamentoId) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(FormaPagamentoController.class).buscar(formaPagamentoId)).withSelfRel();
	}
	
	public Link linkToFormasPagamentoRestaurante(Long restauranteId, String rel) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestauranteFormaPagamentoController.class).listar(restauranteId)).withRel(rel);
	}

	public Link linktoAssociarResponsavelRestaurante(Long restaurante_id, String rel) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestauranteUsuarioResponsavelController.class).associar(restaurante_id, null)).withRel(rel);
	}
	
	public Link linkToResponsaveisRestaurante(Long restauranteId, String rel) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestauranteUsuarioResponsavelController.class).listar(restauranteId)).withRel(rel);
	}

	public Link linktoDesassociarResponsavelRestaurante(Long usuario_id, Long restaurante_id, String rel) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestauranteUsuarioResponsavelController.class).desassociar(restaurante_id, usuario_id)).withRel(rel);
	}

	public Link linkToProdutos(Long restauranteId, String rel) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestauranteProdutoController.class).listar(restauranteId, null)).withRel(rel);
	}
	
	public Link linkToProduto(Long restauranteId, Long produtoId, String rel) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestauranteProdutoController.class)
				.buscar(restauranteId, produtoId))
				.withRel(rel);
	}

	public Link linkToFotoProduto(Long restauranteId, Long produtoId, String rel) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestauranteProdutoController.class).buscarFoto(restauranteId, produtoId)).withRel(rel);
	}
	
	public Link linktoPermissoesGrupo(Long grupoId, String rel) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GrupoPermissaoController.class).buscar(grupoId)).withRel(rel);
	}

	public Link linktoDesassociarPermissao(Long grupoId, Long permissaoId, String rel) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GrupoPermissaoController.class).desassociar(grupoId, permissaoId)).withRel(rel);
	}

	public Link linkToAssociarPermissao(Long grupoId, Long permissaoId, String rel) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GrupoPermissaoController.class).associar(grupoId, permissaoId)).withRel(rel);
	}

	public Link linkToAssociarPermissao(Long grupoId, String rel) {
		return linkToAssociarPermissao(grupoId, null, rel);
	}

	public Link linkToPermissoes(String rel) {
		return WebMvcLinkBuilder.linkTo(PermissaoController.class).withRel(rel);
	}

	public Link linkToDesassociarGrupoUsuario(Long usuarioId, Long grupoId, String rel) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioGrupoController.class).desassociar(usuarioId, grupoId)).withRel(rel);
	}

	public Link linkToAssociarGrupoUsuario(Long usuarioId, String rel) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioGrupoController.class).associar(usuarioId, null)).withRel(rel);
	}
	
}
