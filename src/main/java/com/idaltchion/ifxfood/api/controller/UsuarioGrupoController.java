package com.idaltchion.ifxfood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.idaltchion.ifxfood.api.assembler.GrupoDTOAssembler;
import com.idaltchion.ifxfood.api.model.GrupoDTO;
import com.idaltchion.ifxfood.domain.model.Grupo;
import com.idaltchion.ifxfood.domain.model.Usuario;
import com.idaltchion.ifxfood.domain.service.CadastroGrupoService;
import com.idaltchion.ifxfood.domain.service.UsuarioService;

@RestController
@RequestMapping("/usuarios/{usuario_id}/grupos")
public class UsuarioGrupoController {

	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	CadastroGrupoService grupoService;
	
	@Autowired
	GrupoDTOAssembler grupoAssembler;
	
	@GetMapping
	public List<GrupoDTO> listar(@PathVariable Long usuario_id) {
		Usuario usuarioExistente = usuarioService.buscar(usuario_id);
		return grupoAssembler.toCollectionDTO(usuarioExistente.getGrupos());
	}
	
	@PutMapping("/{grupo_id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void associar(@PathVariable Long usuario_id, @PathVariable Long grupo_id) {
		Usuario usuarioExistente = usuarioService.buscar(usuario_id);
		Grupo grupoExistente = grupoService.buscar(grupo_id);
		usuarioService.associarGrupo(usuarioExistente, grupoExistente);
	}
	
	@DeleteMapping("/{grupo_id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void desassociar(@PathVariable Long usuario_id, @PathVariable Long grupo_id) {
		Usuario usuarioExistente = usuarioService.buscar(usuario_id);
		Grupo grupoExistente = grupoService.buscar(grupo_id);
		usuarioService.desassociar(usuarioExistente, grupoExistente);
	}
	
}
