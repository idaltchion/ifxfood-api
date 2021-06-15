package com.idaltchion.ifxfood.api.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.idaltchion.ifxfood.api.assembler.FotoProdutoDTOAssembler;
import com.idaltchion.ifxfood.api.assembler.FotoProdutoDTODisassembler;
import com.idaltchion.ifxfood.api.assembler.ProdutoDTOAssembler;
import com.idaltchion.ifxfood.api.assembler.ProdutoDTODisassembler;
import com.idaltchion.ifxfood.api.model.FotoProdutoDTO;
import com.idaltchion.ifxfood.api.model.ProdutoDTO;
import com.idaltchion.ifxfood.api.model.input.ProdutoDTOInput;
import com.idaltchion.ifxfood.api.model.input.ProdutoFotoInput;
import com.idaltchion.ifxfood.domain.model.FotoProduto;
import com.idaltchion.ifxfood.domain.model.Produto;
import com.idaltchion.ifxfood.domain.model.Restaurante;
import com.idaltchion.ifxfood.domain.repository.ProdutoRepository;
import com.idaltchion.ifxfood.domain.service.CatalogoFotoProdutoService;
import com.idaltchion.ifxfood.domain.service.CadastroProdutoService;
import com.idaltchion.ifxfood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos")
public class RestauranteProdutoController {
	
	@Autowired
	private CadastroRestauranteService restauranteService;

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ProdutoDTOAssembler produtoDTOAssembler;
	
	@Autowired
	private FotoProdutoDTOAssembler fotoProdutoDTOAssembler;
	
	@Autowired
	private FotoProdutoDTODisassembler fotoProdutoDTODisassembler;
	
	@Autowired
	private ProdutoDTODisassembler produtoDTODisassembler;

	@Autowired
	private CadastroProdutoService produtoService;
	
	@Autowired
	private CatalogoFotoProdutoService fotoProdutoService;
	
	@GetMapping
	public List<ProdutoDTO> listar(@PathVariable Long restauranteId, 
			@RequestParam(required = false) boolean exibirInativos) {
		Restaurante restaurante = restauranteService.buscar(restauranteId);
		List<Produto> produtos = null;
		if (exibirInativos) {
			produtos = produtoRepository.findAllByRestaurante(restaurante);
		} else {
			produtos = produtoRepository.findAtivosByRestaurante(restaurante);
		}
		return produtoDTOAssembler.toDTOCollection(produtos);
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
	
	@PutMapping(path  = "/{produtoId}/foto", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public FotoProdutoDTO atualizarFoto(@PathVariable Long restauranteId, 
			@PathVariable Long produtoId, @Valid ProdutoFotoInput produtoFotoInput) throws IOException {
		
		var produto = produtoService.buscar(produtoId, restauranteId);
		var arquivo = produtoFotoInput.getArquivo();
		
		FotoProduto foto = new FotoProduto();
		foto.setContentType(arquivo.getContentType());
		foto.setDescricao(produtoFotoInput.getDescricao());
		foto.setNomeArquivo(arquivo.getOriginalFilename());
		foto.setTamanho(arquivo.getSize());
		foto.setProduto(produto);
		
		var inputStream = arquivo.getInputStream();
		
		FotoProduto fotoSalva = fotoProdutoService.salvar(foto, inputStream);
		
		return fotoProdutoDTOAssembler.toDTO(fotoSalva);
	}
	
	@GetMapping(path = "/{produtoId}/foto")
	public FotoProdutoDTO buscarFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
		FotoProduto foto = fotoProdutoService.buscar(restauranteId, produtoId);
		return fotoProdutoDTODisassembler.toDTO(foto);
	}
	
}
