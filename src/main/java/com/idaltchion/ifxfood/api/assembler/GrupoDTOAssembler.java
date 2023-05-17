package com.idaltchion.ifxfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.idaltchion.ifxfood.api.IfxLinks;
import com.idaltchion.ifxfood.api.controller.GrupoController;
import com.idaltchion.ifxfood.api.model.GrupoDTO;
import com.idaltchion.ifxfood.domain.model.Grupo;

@Component
public class GrupoDTOAssembler extends RepresentationModelAssemblerSupport<Grupo, GrupoDTO> {

	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	private IfxLinks ifxLinks;
	
	public GrupoDTOAssembler() {
		super(GrupoController.class, GrupoDTO.class);
	}
	
	@Override	
	public GrupoDTO toModel(Grupo grupo) {
		GrupoDTO grupoDTO = createModelWithId(grupo.getId(), grupo);
		modelMapper.map(grupo, grupoDTO);
		grupoDTO.add(ifxLinks.linktoPermissoesGrupo(grupo.getId(), "permissoes"));
		
		return grupoDTO;
	}
	
	public GrupoDTO toModelWithCollectionRel(Grupo grupo) {
		GrupoDTO grupoDTO = toModel(grupo);
		grupoDTO.add(ifxLinks.linkToGrupos(IanaLinkRelations.COLLECTION_VALUE));
		
		return grupoDTO;
	}
	
	@Override
	public CollectionModel<GrupoDTO> toCollectionModel(Iterable<? extends Grupo> grupos) {
		return super.toCollectionModel(grupos).add(ifxLinks.linkToGrupos(IanaLinkRelations.SELF_VALUE));
	}
	
	public CollectionModel<GrupoDTO> toCollectionModelWithAssociarDesassociarGrupo(Iterable<? extends Grupo> grupos, Long usuarioId) {
		CollectionModel<GrupoDTO> gruposCollectionModel = toCollectionModel(grupos)
				.removeLinks()
				.add(ifxLinks.linkToAssociarGrupoUsuario(usuarioId, "associar"));
		
		gruposCollectionModel.getContent().forEach(
				grupo -> grupo.add(ifxLinks.linkToDesassociarGrupoUsuario(usuarioId, grupo.getId(), "desassociar")));
		
		return gruposCollectionModel;
	}

}
