package com.idaltchion.ifxfood.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.idaltchion.ifxfood.api.v1.assembler.GrupoDTOAssembler;
import com.idaltchion.ifxfood.api.v1.model.GrupoDTO;
import com.idaltchion.ifxfood.domain.model.Grupo;
import com.idaltchion.ifxfood.domain.model.Usuario;
import com.idaltchion.ifxfood.domain.service.CadastroGrupoService;
import com.idaltchion.ifxfood.domain.service.CadastroUsuarioService;

@RestController
@RequestMapping("/v1/usuarios/{usuario_id}/grupos")
public class UsuarioGrupoController {

	@Autowired
	CadastroUsuarioService usuarioService;
	
	@Autowired
	CadastroGrupoService grupoService;
	
	@Autowired
	GrupoDTOAssembler grupoAssembler;
	
	@GetMapping
	public CollectionModel<GrupoDTO> listar(@PathVariable Long usuario_id) {
		Usuario usuarioExistente = usuarioService.buscar(usuario_id);
		return grupoAssembler.toCollectionModelWithAssociarDesassociarGrupo(usuarioExistente.getGrupos(), usuario_id);
	}
	
	@PutMapping("/{grupo_id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> associar(@PathVariable Long usuario_id, @PathVariable Long grupo_id) {
		Usuario usuarioExistente = usuarioService.buscar(usuario_id);
		Grupo grupoExistente = grupoService.buscar(grupo_id);
		usuarioService.associarGrupo(usuarioExistente, grupoExistente);
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{grupo_id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> desassociar(@PathVariable Long usuario_id, @PathVariable Long grupo_id) {
		Usuario usuarioExistente = usuarioService.buscar(usuario_id);
		Grupo grupoExistente = grupoService.buscar(grupo_id);
		usuarioService.desassociar(usuarioExistente, grupoExistente);
		
		return ResponseEntity.noContent().build();
	}
	
}
