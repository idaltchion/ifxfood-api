package com.idaltchion.ifxfood.api.v2.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.idaltchion.ifxfood.api.v2.IfxLinksV2;
import com.idaltchion.ifxfood.api.v2.controller.EstadoControllerV2;
import com.idaltchion.ifxfood.api.v2.model.EstadoDTOV2;
import com.idaltchion.ifxfood.domain.model.Estado;

/*
 * Classe responsavel por transformar um objeto do tipo Modelo de Dominio para objeto de Modelo de Representacao 
 */
@Component
public class EstadoDTOAssemblerV2 extends RepresentationModelAssemblerSupport<Estado, EstadoDTOV2> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private IfxLinksV2 ifxLinks;

	public EstadoDTOAssemblerV2() {
		super(EstadoControllerV2.class, EstadoDTOV2.class);
	}

	@Override
	public EstadoDTOV2 toModel(Estado estado) {
		EstadoDTOV2 estadoDTO = createModelWithId(estado.getId(), estado);
		modelMapper.map(estado, estadoDTO);

		return estadoDTO;
	}

	@Override
	public CollectionModel<EstadoDTOV2> toCollectionModel(Iterable<? extends Estado> estados) {
		return super.toCollectionModel(estados)
				.add(ifxLinks.linkToEstados("estados"));
	}

	public EstadoDTOV2 toModelWithCollectionRel(Estado estado) {
		EstadoDTOV2 estadoDTO = toModel(estado);
		estadoDTO.add(ifxLinks.linkToEstados("estados"));

		return estadoDTO;
	}

}
