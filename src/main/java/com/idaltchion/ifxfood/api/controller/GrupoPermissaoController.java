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

import com.idaltchion.ifxfood.api.assembler.PermissaoDTOAssembler;
import com.idaltchion.ifxfood.domain.model.Grupo;
import com.idaltchion.ifxfood.domain.model.Permissao;
import com.idaltchion.ifxfood.domain.model.PermissaoDTO;
import com.idaltchion.ifxfood.domain.service.CadastroGrupoService;
import com.idaltchion.ifxfood.domain.service.CadastroPermissaoService;

@RestController
@RequestMapping("/grupos/{grupo_id}/permissoes")
public class GrupoPermissaoController {
	
	@Autowired
	CadastroGrupoService grupoService;

	@Autowired
	PermissaoDTOAssembler permissaoAssembler;
	
	@Autowired
	CadastroPermissaoService permissaoService;
	
	@GetMapping
	public List<PermissaoDTO> buscar(@PathVariable Long grupo_id) {
		Grupo grupo = grupoService.buscar(grupo_id);
		return permissaoAssembler.toCollectionModel(grupo.getPermissoes());
	}
	
	@PutMapping("/{permissao_id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void associar(@PathVariable Long grupo_id, @PathVariable Long permissao_id) {
		Grupo grupoExistente = grupoService.buscar(grupo_id);
		Permissao permissaoExistente = permissaoService.buscar(permissao_id);
		permissaoService.associar(grupoExistente, permissaoExistente);
	}
	
	@DeleteMapping("/{permissao_id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void desassociar(@PathVariable Long grupo_id, @PathVariable Long permissao_id) {
		Grupo grupoExistente = grupoService.buscar(grupo_id);
		Permissao permissaoExistente = permissaoService.buscar(permissao_id);
		permissaoService.desassociar(grupoExistente, permissaoExistente);
	}
	
}
