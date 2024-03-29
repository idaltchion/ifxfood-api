package com.idaltchion.ifxfood.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idaltchion.ifxfood.api.v1.IfxLinks;

@RestController
@RequestMapping(path = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class RootEntryPointController {

	@Autowired
	private IfxLinks ifxLinks;
	
	@GetMapping
	public RootEntryPointModel root() {
		var rootEntryPoint = new RootEntryPointModel();
		rootEntryPoint.add(ifxLinks.linkToRestaurantes("restaurantes"));
		rootEntryPoint.add(ifxLinks.linkToPedidos("pedidos"));
		rootEntryPoint.add(ifxLinks.linkToCozinhas("cozinhas"));
		rootEntryPoint.add(ifxLinks.linkToEstados("estados"));
		rootEntryPoint.add(ifxLinks.linkToCidades("cidades"));
		rootEntryPoint.add(ifxLinks.linkToPermissoes("permissoes"));
		rootEntryPoint.add(ifxLinks.linkToFormasPagamento("formas-pagamento"));
		rootEntryPoint.add(ifxLinks.linkToGrupos("grupos"));
		rootEntryPoint.add(ifxLinks.linkToUsuarios("usuarios"));
		rootEntryPoint.add(ifxLinks.linkToEstatisticas("estatisticas"));
		
		return rootEntryPoint;
	}
	
	private static class RootEntryPointModel extends RepresentationModel<RootEntryPointModel> {
	}
	
}
