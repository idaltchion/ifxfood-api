package com.idaltchion.ifxfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.idaltchion.ifxfood.api.IfxLinks;
import com.idaltchion.ifxfood.api.controller.UsuarioController;
import com.idaltchion.ifxfood.api.model.UsuarioDTO;
import com.idaltchion.ifxfood.domain.model.Usuario;

@Component
public class UsuarioDTOAssembler extends RepresentationModelAssemblerSupport<Usuario, UsuarioDTO> {

	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	private IfxLinks ifxLinks;

	public UsuarioDTOAssembler() {
		super(UsuarioController.class, UsuarioDTO.class);
	}
	
	@Override
	public UsuarioDTO toModel(Usuario usuario) {
		UsuarioDTO usuarioDTO = createModelWithId(usuario.getId(), usuario);
		modelMapper.map(usuario, usuarioDTO);
		usuarioDTO.add(ifxLinks.linkToGruposUsuario(usuarioDTO.getId()));
		
		return usuarioDTO;
	}

	@Override
	public CollectionModel<UsuarioDTO> toCollectionModel(Iterable<? extends Usuario> usuarios) {
		return super.toCollectionModel(usuarios).add(WebMvcLinkBuilder.linkTo(UsuarioController.class).withSelfRel());
	}
	
	public CollectionModel<UsuarioDTO> toCollectionModelWithAssociarResponsavel(Iterable<? extends Usuario> usuarios, Long restaurante_id) {
		CollectionModel<UsuarioDTO> usuariosCollectionModel = toCollectionModel(usuarios);
		usuariosCollectionModel.getContent().forEach(usuario ->
				usuario.add(ifxLinks.linktoDesassociarResponsavelRestaurante(usuario.getId(), restaurante_id, "desassociar")));
		return usuariosCollectionModel;
	}
	
	public UsuarioDTO toModelWithCollectionRel(Usuario usuario) {
		UsuarioDTO usuarioDTO = toModel(usuario);
		usuarioDTO.add(ifxLinks.linkToUsuarios("usuarios"));
		return usuarioDTO;
	}

}
