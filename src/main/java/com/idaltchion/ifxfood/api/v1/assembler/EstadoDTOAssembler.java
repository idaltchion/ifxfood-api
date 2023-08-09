package com.idaltchion.ifxfood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.idaltchion.ifxfood.api.v1.IfxLinks;
import com.idaltchion.ifxfood.api.v1.controller.EstadoController;
import com.idaltchion.ifxfood.api.v1.model.EstadoDTO;
import com.idaltchion.ifxfood.domain.model.Estado;

/*
 * Classe responsavel por transformar um objeto do tipo Modelo de Dominio para objeto de Modelo de Representacao 
 */
@Component
public class EstadoDTOAssembler extends RepresentationModelAssemblerSupport<Estado, EstadoDTO> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private IfxLinks ifxLinks;

	public EstadoDTOAssembler() {
		super(EstadoController.class, EstadoDTO.class);
	}

	@Override
	public EstadoDTO toModel(Estado estado) {
		EstadoDTO estadoDTO = createModelWithId(estado.getId(), estado);
		modelMapper.map(estado, estadoDTO);

		return estadoDTO;
	}

	@Override
	public CollectionModel<EstadoDTO> toCollectionModel(Iterable<? extends Estado> estados) {
		return super.toCollectionModel(estados)
				.add(ifxLinks.linkToEstados("estados"));
	}

	public EstadoDTO toModelWithCollectionRel(Estado estado) {
		EstadoDTO estadoDTO = toModel(estado);
		estadoDTO.add(ifxLinks.linkToEstados("estados"));

		return estadoDTO;
	}

}
