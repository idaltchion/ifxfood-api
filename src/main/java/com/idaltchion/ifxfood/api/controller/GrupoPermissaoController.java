package com.idaltchion.ifxfood.api.controller;

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

import com.idaltchion.ifxfood.api.assembler.GrupoPermissaoDTOAssembler;
import com.idaltchion.ifxfood.api.model.PermissaoDTO;
import com.idaltchion.ifxfood.domain.model.Grupo;
import com.idaltchion.ifxfood.domain.model.Permissao;
import com.idaltchion.ifxfood.domain.service.CadastroGrupoService;
import com.idaltchion.ifxfood.domain.service.CadastroPermissaoService;

@RestController
@RequestMapping("/grupos/{grupo_id}/permissoes")
public class GrupoPermissaoController {
	
	@Autowired
	CadastroGrupoService grupoService;

	@Autowired
	GrupoPermissaoDTOAssembler permissaoAssembler;
	
	@Autowired
	CadastroPermissaoService permissaoService;
	
	@GetMapping
	public CollectionModel<PermissaoDTO> buscar(@PathVariable Long grupo_id) {
		Grupo grupo = grupoService.buscar(grupo_id);
		return permissaoAssembler.toCollectionModelWithDesassociarPermissao(grupo.getPermissoes(), grupo_id);
	}
	
	@PutMapping("/{permissao_id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> associar(@PathVariable Long grupo_id, @PathVariable Long permissao_id) {
		Grupo grupoExistente = grupoService.buscar(grupo_id);
		Permissao permissaoExistente = permissaoService.buscar(permissao_id);
		permissaoService.associar(grupoExistente, permissaoExistente);
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{permissao_id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> desassociar(@PathVariable Long grupo_id, @PathVariable Long permissao_id) {
		Grupo grupoExistente = grupoService.buscar(grupo_id);
		Permissao permissaoExistente = permissaoService.buscar(permissao_id);
		permissaoService.desassociar(grupoExistente, permissaoExistente);
		
		return ResponseEntity.noContent().build();
	}
	
}
