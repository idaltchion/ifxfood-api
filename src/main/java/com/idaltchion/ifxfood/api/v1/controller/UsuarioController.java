package com.idaltchion.ifxfood.api.v1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.idaltchion.ifxfood.api.v1.assembler.UsuarioDTOAssembler;
import com.idaltchion.ifxfood.api.v1.assembler.UsuarioDTODisassembler;
import com.idaltchion.ifxfood.api.v1.model.SenhaDTOInput;
import com.idaltchion.ifxfood.api.v1.model.UsuarioDTO;
import com.idaltchion.ifxfood.api.v1.model.input.UsuarioComSenhaDTOInput;
import com.idaltchion.ifxfood.api.v1.model.input.UsuarioDTOInput;
import com.idaltchion.ifxfood.domain.model.Usuario;
import com.idaltchion.ifxfood.domain.repository.UsuarioRepository;
import com.idaltchion.ifxfood.domain.service.CadastroUsuarioService;

@RestController
@RequestMapping(path = "/usuarios")
public class UsuarioController {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	UsuarioDTOAssembler usuarioDTOAssembler;
	
	@Autowired
	UsuarioDTODisassembler usuarioDTODisassembler;
	
	@Autowired
	CadastroUsuarioService usuarioService;
	
	@GetMapping
	public CollectionModel<UsuarioDTO> listar() {
		return usuarioDTOAssembler.toCollectionModel(usuarioRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public UsuarioDTO buscar(@PathVariable Long id) {
		return usuarioDTOAssembler.toModelWithCollectionRel(usuarioService.buscar(id));
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public UsuarioDTO adicionar(@RequestBody @Valid UsuarioComSenhaDTOInput usuarioInput) {
		Usuario novoUsuario = usuarioDTODisassembler.toModelObject(usuarioInput);
		return usuarioDTOAssembler.toModel(usuarioService.salvar(novoUsuario));
	}
	
	@PutMapping("/{id}")
	public UsuarioDTO atualizar(@PathVariable Long id, @RequestBody @Valid UsuarioDTOInput usuarioInput) {
		Usuario usuarioAtual = usuarioService.buscar(id);
		usuarioDTODisassembler.copyToModelObject(usuarioInput, usuarioAtual);
		return usuarioDTOAssembler.toModel(usuarioService.salvar(usuarioAtual));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		usuarioService.deletar(id);
	}
	
	@PutMapping("/{id}/senha")
	public void alterarSenha(@PathVariable Long id, @RequestBody @Valid SenhaDTOInput senhaInput) {
		usuarioService.alterarSenha(id, senhaInput);
	}
	
}
