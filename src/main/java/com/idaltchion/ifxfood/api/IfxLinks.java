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
import com.idaltchion.ifxfood.api.controller.FormaPagamentoController;
import com.idaltchion.ifxfood.api.controller.PedidoController;
import com.idaltchion.ifxfood.api.controller.RestauranteController;
import com.idaltchion.ifxfood.api.controller.RestauranteProdutoController;
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
	
	public Link linkToCidades(Long cidadeId) {
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
	
	public Link linkToEstados(Long estadoId) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EstadoController.class).buscar(estadoId)).withSelfRel();
	}

	public Link linkToCozinhas() {
		return WebMvcLinkBuilder.linkTo(CozinhaController.class).withRel(IanaLinkRelations.COLLECTION);
	}

	public Link linkToFormasPagamento(Long formaPagamentoId) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(FormaPagamentoController.class).buscar(formaPagamentoId)).withSelfRel();
	}

	public Link linkToRestaurantes(Long restauranteId) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestauranteController.class).buscar(restauranteId)).withSelfRel();
	}

	public Link linkToUsuarios() {
		return WebMvcLinkBuilder.linkTo(UsuarioController.class).withRel("usuarios");
	}
	
	public Link linkToUsuarios(Long usuarioId) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).buscar(usuarioId)).withSelfRel();
	}

	public Link linkToGruposUsuario(Long usuarioId) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioGrupoController.class).listar(usuarioId)).withRel("grupos-usuario");
	}
	
	public Link linkToProdutos(Long restauranteId, Long produtoId) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestauranteProdutoController.class)
				.buscar(restauranteId, produtoId))
				.withSelfRel();
	}

	

	

	

	
	
}
