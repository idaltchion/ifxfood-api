package com.idaltchion.ifxfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.idaltchion.ifxfood.api.IfxLinks;
import com.idaltchion.ifxfood.api.controller.GrupoPermissaoController;
import com.idaltchion.ifxfood.api.model.PermissaoDTO;
import com.idaltchion.ifxfood.domain.model.Permissao;

@Component
public class GrupoPermissaoDTOAssembler extends RepresentationModelAssemblerSupport<Permissao, PermissaoDTO> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private IfxLinks ifxLinks;
	
	public GrupoPermissaoDTOAssembler() {
		super(GrupoPermissaoController.class, PermissaoDTO.class);
	}
	
	@Override
	public PermissaoDTO toModel(Permissao permissao) {
		return modelMapper.map(permissao, PermissaoDTO.class);		
	}
	
	
//	public List<PermissaoDTO> toCollectionModel(Set<Permissao> permissoes) {
//		return permissoes.stream()
//				.map(permissao -> toModel(permissao))
//				.collect(Collectors.toList());
//	}
	
	@Override
	public CollectionModel<PermissaoDTO> toCollectionModel(Iterable<? extends Permissao> permissoes) {
		return super.toCollectionModel(permissoes);
	}
	
	public CollectionModel<PermissaoDTO> toCollectionModelWithDesassociarPermissao(Iterable<? extends Permissao> permissoes, Long grupoId) {
		CollectionModel<PermissaoDTO> permissoesCollection = toCollectionModel(permissoes)
				.add(ifxLinks.linktoPermissoesGrupo(grupoId, IanaLinkRelations.SELF_VALUE))
				.add(ifxLinks.linkToAssociarPermissao(grupoId, "associar"));
		
		permissoesCollection.getContent().forEach(
				permissao -> permissao.add(ifxLinks.linktoDesassociarPermissao(grupoId, permissao.getId(), "desassociar")));
		
		return permissoesCollection;
	}
	
}
