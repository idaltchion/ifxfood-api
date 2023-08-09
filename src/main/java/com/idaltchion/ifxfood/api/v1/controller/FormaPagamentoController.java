package com.idaltchion.ifxfood.api.v1.controller;

import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.idaltchion.ifxfood.api.v1.assembler.FormaPagamentoDTOAssembler;
import com.idaltchion.ifxfood.api.v1.assembler.FormaPagamentoDTODisassembler;
import com.idaltchion.ifxfood.api.v1.model.FormaPagamentoDTO;
import com.idaltchion.ifxfood.api.v1.model.input.FormaPagamentoDTOInput;
import com.idaltchion.ifxfood.api.v1.openapi.controller.FormaPagamentoControllerOpenAPI;
import com.idaltchion.ifxfood.domain.model.FormaPagamento;
import com.idaltchion.ifxfood.domain.service.CadastroFormaPagamentoService;

@RestController
@RequestMapping("/formas-pagamento")
public class FormaPagamentoController implements FormaPagamentoControllerOpenAPI {

	@Autowired
	private CadastroFormaPagamentoService cadastroFormaPagamentoService;
	
	@Autowired
	private FormaPagamentoDTOAssembler formaPagamentoDTOAssembler;
	
	@Autowired
	private FormaPagamentoDTODisassembler formaPagamentoDTODisassembler;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CollectionModel<FormaPagamentoDTO>> listar() {
		CollectionModel<FormaPagamentoDTO> formasPagamento = formaPagamentoDTOAssembler.toCollectionModel(cadastroFormaPagamentoService.listar());
		
		return ResponseEntity
				.ok()
				.cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
				.body(formasPagamento);
	}
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FormaPagamentoDTO> buscar(@PathVariable Long id) {
		FormaPagamento formaPagamento = cadastroFormaPagamentoService.buscar(id);
		FormaPagamentoDTO formaPagamentoDTO = formaPagamentoDTOAssembler.toModelWithCollectionRel(formaPagamento);
		return ResponseEntity
				.ok()
				.cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
				.body(formaPagamentoDTO);
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.CREATED)
	public FormaPagamentoDTO adicionar(@RequestBody @Valid FormaPagamentoDTOInput formaPagamentoInput) {
		FormaPagamento formaPagamento = formaPagamentoDTODisassembler.toModelObject(formaPagamentoInput);
		return formaPagamentoDTOAssembler.toModel(cadastroFormaPagamentoService.adicionar(formaPagamento));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		cadastroFormaPagamentoService.remover(id);
	}
	
	@PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public FormaPagamentoDTO atualizar(@PathVariable Long id, @RequestBody @Valid FormaPagamentoDTOInput formaPagamentoInput) {
		FormaPagamento formaPagamentoAtual = cadastroFormaPagamentoService.buscar(id);
		formaPagamentoDTODisassembler.copyToDomainObject(formaPagamentoInput, formaPagamentoAtual);
		return formaPagamentoDTOAssembler.toModel(cadastroFormaPagamentoService.salvar(formaPagamentoAtual));
	}
	
}
