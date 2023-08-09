package com.idaltchion.ifxfood.api.v1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.idaltchion.ifxfood.api.v1.assembler.GrupoDTOAssembler;
import com.idaltchion.ifxfood.api.v1.assembler.GrupoDTODisassembler;
import com.idaltchion.ifxfood.api.v1.model.GrupoDTO;
import com.idaltchion.ifxfood.api.v1.model.input.GrupoDTOInput;
import com.idaltchion.ifxfood.api.v1.openapi.controller.GrupoControllerOpenAPI;
import com.idaltchion.ifxfood.domain.model.Grupo;
import com.idaltchion.ifxfood.domain.repository.GrupoRepository;
import com.idaltchion.ifxfood.domain.service.CadastroGrupoService;

@RestController
@RequestMapping(path = "/grupos", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoController implements GrupoControllerOpenAPI {	
	
	@Autowired
	GrupoRepository grupoRepository;

	@Autowired
	GrupoDTOAssembler grupoDTOAssembler;
	
	@Autowired
	GrupoDTODisassembler grupoDTODisassembler;
	
	@Autowired
	CadastroGrupoService grupoService;
	
	@GetMapping
	public CollectionModel<GrupoDTO> listar() {
		return grupoDTOAssembler.toCollectionModel(grupoRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public GrupoDTO buscar(@PathVariable Long id) {
		return grupoDTOAssembler.toModelWithCollectionRel(grupoService.buscar(id));
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public GrupoDTO adicionar(@RequestBody @Valid GrupoDTOInput grupoInput) {
		Grupo novoGrupo = grupoDTODisassembler.toDomainObject(grupoInput);
		return grupoDTOAssembler.toModel(grupoService.salvar(novoGrupo));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		grupoService.deletar(id);
	}
	
	@PutMapping("/{id}")
	public GrupoDTO atualizar(@PathVariable Long id, @RequestBody @Valid GrupoDTOInput grupoInput) {
		Grupo grupoAtual = grupoService.buscar(id);
		grupoDTODisassembler.copyToDomainObject(grupoInput, grupoAtual);
		grupoAtual = grupoService.salvar(grupoAtual);
		return grupoDTOAssembler.toModel(grupoAtual);
	}
	
}
