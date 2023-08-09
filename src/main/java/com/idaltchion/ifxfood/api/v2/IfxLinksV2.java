package com.idaltchion.ifxfood.api.v2;

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.idaltchion.ifxfood.api.v1.controller.CidadeController;

@Component
public class IfxLinksV2 {
	
	public Link linkToCidades(String rel) {
		return WebMvcLinkBuilder.linkTo(CidadeController.class).withRel(rel);
	}
	
	//TODO: Ajustar link rel para um recurso especifico de Cidade
	public Link linkToCidades() {
		return linkToCidades(IanaLinkRelations.SELF.value());
	}
		
}
