package com.idaltchion.ifxfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.ctc.wstx.shaded.msv_core.reader.Controller;
import com.idaltchion.ifxfood.api.IfxLinks;
import com.idaltchion.ifxfood.api.model.CidadeDTO;
import com.idaltchion.ifxfood.domain.model.Cidade;

@Component
public class CidadeDTOAssember extends RepresentationModelAssemblerSupport<Cidade, CidadeDTO> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private IfxLinks ifxLinks;
	
	public CidadeDTOAssember() {
		super(Controller.class, CidadeDTO.class);
	}
	
	@Override
	public CidadeDTO toModel(Cidade cidade) {
		//outro metodo de criar self link de um recurso (withSelfRel)
		CidadeDTO cidadeDTO = createModelWithId(cidade.getId(), cidade);
		
		modelMapper.map(cidade, cidadeDTO);
//		cidadeDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CidadeController.class).buscar(cidadeDTO.getId())).withSelfRel());
		cidadeDTO.getEstado().add(ifxLinks.linkToEstados(cidadeDTO.getEstado().getId()));
	
		return cidadeDTO;
	}
	
	public CidadeDTO toModelWithCollectionRel(Cidade cidade) {
		CidadeDTO cidadeDTO = toModel(cidade);
		cidadeDTO.add(ifxLinks.linkToCidades(IanaLinkRelations.COLLECTION_VALUE));
		return cidadeDTO;
	}
	
	@Override
	public CollectionModel<CidadeDTO> toCollectionModel(Iterable<? extends Cidade> cidades) {
		return super.toCollectionModel(cidades).add(ifxLinks.linkToCidades(IanaLinkRelations.SELF_VALUE));
	}
	
}
