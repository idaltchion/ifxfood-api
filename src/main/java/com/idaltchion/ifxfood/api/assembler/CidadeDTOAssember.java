package com.idaltchion.ifxfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.ctc.wstx.shaded.msv_core.reader.Controller;
import com.idaltchion.ifxfood.api.controller.CidadeController;
import com.idaltchion.ifxfood.api.controller.EstadoController;
import com.idaltchion.ifxfood.api.model.CidadeDTO;
import com.idaltchion.ifxfood.domain.model.Cidade;

@Component
public class CidadeDTOAssember extends RepresentationModelAssemblerSupport<Cidade, CidadeDTO> {

	@Autowired
	private ModelMapper modelMapper;
	
	public CidadeDTOAssember() {
		super(Controller.class, CidadeDTO.class);
	}
	
	@Override
	public CidadeDTO toModel(Cidade cidade) {
		//outro metodo de criar self link de um recurso (withSelfRel)
		CidadeDTO cidadeDTO = createModelWithId(cidade.getId(), cidade);
		
		modelMapper.map(cidade, cidadeDTO);
//		cidadeDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CidadeController.class).buscar(cidadeDTO.getId())).withSelfRel());
		cidadeDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CidadeController.class).listar()).withRel(IanaLinkRelations.COLLECTION));
		cidadeDTO.getEstado().add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EstadoController.class).buscar(cidadeDTO.getEstado().getId())).withSelfRel());
	
		return cidadeDTO;
	}
	
	@Override
	public CollectionModel<CidadeDTO> toCollectionModel(Iterable<? extends Cidade> cidades) {
		return super.toCollectionModel(cidades).add(WebMvcLinkBuilder.linkTo(CidadeController.class).withSelfRel());
	}
	
}
