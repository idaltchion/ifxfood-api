package com.idaltchion.ifxfood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.idaltchion.ifxfood.api.v1.IfxLinks;
import com.idaltchion.ifxfood.api.v1.controller.RestauranteController;
import com.idaltchion.ifxfood.api.v1.model.RestauranteDTO;
import com.idaltchion.ifxfood.domain.model.Restaurante;

@Component
public class RestauranteDTOAssembler extends RepresentationModelAssemblerSupport<Restaurante, RestauranteDTO> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private IfxLinks ifxLinks;
	
	public RestauranteDTOAssembler() {
		super(RestauranteController.class, RestauranteDTO.class);
	}
	
	/*
	 * efetua a conversao do objeto de Modelo de Dominio para objeto de Modelo de Representacao (DTO de Saida)
	 */
	@Override
	public RestauranteDTO toModel(Restaurante restaurante) {
		Long restauranteId = restaurante.getId();
		RestauranteDTO restauranteDTO = createModelWithId(restauranteId, restaurante);
		modelMapper.map(restaurante, restauranteDTO);
		restauranteDTO.add(ifxLinks.linkToRestaurantes(IanaLinkRelations.COLLECTION_VALUE));
		restauranteDTO.getCozinha().add(ifxLinks.linkToCozinha(restauranteDTO.getCozinha().getId()));
		restauranteDTO.add(ifxLinks.linkToFormasPagamentoRestaurante(restauranteId, "formas-pagamento"));
		restauranteDTO.add(ifxLinks.linkToResponsaveisRestaurante(restauranteId, "responsaveis"));
		restauranteDTO.add(ifxLinks.linkToProdutos(restauranteId, "produtos"));
		
		if (restaurante.getEndereco() != null) {
			restauranteDTO.getEndereco().getCidade().add(ifxLinks.linkToCidade(restauranteDTO.getEndereco().getCidade().getId()));
		}
		
		if (restaurante.inativacaoPermitida()) {
			restauranteDTO.add(ifxLinks.linkToInativarRestaurante(restauranteId));
		}
		
		if (restaurante.ativacaoPermitida()) {
			restauranteDTO.add(ifxLinks.linkToAtivarRestaurante(restauranteId));
		}
		
		if (restaurante.aberturaPermitida()) {
			restauranteDTO.add(ifxLinks.linkToAbrirRestaurante(restauranteId));
		} 
		
		if (restaurante.fechamentoPermitido()) {
			restauranteDTO.add(ifxLinks.linkToFecharRestaurante(restauranteId));
		}
		
		return restauranteDTO;
	}
	
	@Override
	public CollectionModel<RestauranteDTO> toCollectionModel(Iterable<? extends Restaurante> restaurantes) {
		return super.toCollectionModel(restaurantes).add(WebMvcLinkBuilder.linkTo(RestauranteController.class).withSelfRel());
	}
	
}
