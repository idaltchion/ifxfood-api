package com.idaltchion.ifxfood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.idaltchion.ifxfood.api.assembler.ProdutoDTOAssembler;
import com.idaltchion.ifxfood.api.assembler.ProdutoDTODisassembler;
import com.idaltchion.ifxfood.api.model.ProdutoDTO;
import com.idaltchion.ifxfood.api.model.input.ProdutoDTOInput;
import com.idaltchion.ifxfood.domain.model.Produto;
import com.idaltchion.ifxfood.domain.model.Restaurante;
import com.idaltchion.ifxfood.domain.repository.ProdutoRepository;
import com.idaltchion.ifxfood.domain.service.CadastroProdutoService;
import com.idaltchion.ifxfood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos")
public class RestauranteProdutoController {

	@Autowired
	ProdutoDTOAssembler produtoAssembler;
	
	@Autowired
	CadastroRestauranteService restauranteService;

	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	ProdutoDTOAssembler produtoDTOAssembler;
	
	@Autowired
	private ProdutoDTODisassembler produtoDTODisassembler;

	@Autowired
	private CadastroProdutoService produtoService;
	
	@GetMapping
	public List<ProdutoDTO> listar(@PathVariable Long restauranteId) {
		Restaurante restaurante = restauranteService.buscar(restauranteId);
		return produtoAssembler.toDTOCollection(produtoRepository.findByRestaurante(restaurante));
	}
	
	@GetMapping("/{produtoId}")
	public ProdutoDTO buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
		Produto produto = produtoService.buscar(produtoId, restauranteId);
		return produtoDTOAssembler.toDTO(produto);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProdutoDTO adicionar(@RequestBody @Valid ProdutoDTOInput produtoInput, @PathVariable Long restauranteId) {
		Restaurante restaurante = restauranteService.buscar(restauranteId);
		Produto produto = produtoDTODisassembler.toObjectModel(produtoInput);
		produto.setRestaurante(restaurante);
		return produtoDTOAssembler.toDTO(produtoService.salvar(produto));
	}
	
	@PutMapping("/{produtoId}")
	public ProdutoDTO atualizar(@PathVariable Long produtoId, @PathVariable Long restauranteId, @RequestBody @Valid ProdutoDTOInput produtoInput) {
		Produto produtoAtual = produtoService.buscar(produtoId, restauranteId);
		produtoDTODisassembler.copyToDomainObject(produtoInput, produtoAtual);
		produtoAtual = produtoService.salvar(produtoAtual);
		return produtoDTOAssembler.toDTO(produtoAtual);
	}
	
	@DeleteMapping("/{produtoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long produtoId, @PathVariable Long restauranteId) {
		Produto produto = produtoService.buscar(produtoId, restauranteId);
		produtoService.remover(produto);
	}
	
}
