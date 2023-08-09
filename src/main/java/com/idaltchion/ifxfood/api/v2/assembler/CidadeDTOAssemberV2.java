package com.idaltchion.ifxfood.api.v2.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.ctc.wstx.shaded.msv_core.reader.Controller;
import com.idaltchion.ifxfood.api.v1.IfxLinks;
import com.idaltchion.ifxfood.api.v2.model.CidadeDTOV2;
import com.idaltchion.ifxfood.domain.model.Cidade;

@Component
public class CidadeDTOAssemberV2 extends RepresentationModelAssemblerSupport<Cidade, CidadeDTOV2> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private IfxLinks ifxLinks;
	
	public CidadeDTOAssemberV2() {
		super(Controller.class, CidadeDTOV2.class);
	}
	
	@Override
	public CidadeDTOV2 toModel(Cidade cidade) {
		//outro metodo de criar self link de um recurso (withSelfRel)
		CidadeDTOV2 cidadeDTO = createModelWithId(cidade.getId(), cidade);
		modelMapper.map(cidade, cidadeDTO);
	
		return cidadeDTO;
	}
	
	public CidadeDTOV2 toModelWithCollectionRel(Cidade cidade) {
		CidadeDTOV2 cidadeDTO = toModel(cidade);
		cidadeDTO.add(ifxLinks.linkToCidades(IanaLinkRelations.COLLECTION_VALUE));
		return cidadeDTO;
	}
	
	@Override
	public CollectionModel<CidadeDTOV2> toCollectionModel(Iterable<? extends Cidade> cidades) {
		return super.toCollectionModel(cidades).add(ifxLinks.linkToCidades(IanaLinkRelations.SELF_VALUE));
	}
	
}
