package com.idaltchion.ifxfood.api.v1.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.idaltchion.ifxfood.api.v1.IfxLinks;
import com.idaltchion.ifxfood.api.v1.assembler.FotoProdutoDTOAssembler;
import com.idaltchion.ifxfood.api.v1.assembler.ProdutoDTOAssembler;
import com.idaltchion.ifxfood.api.v1.assembler.ProdutoDTODisassembler;
import com.idaltchion.ifxfood.api.v1.model.FotoProdutoDTO;
import com.idaltchion.ifxfood.api.v1.model.ProdutoDTO;
import com.idaltchion.ifxfood.api.v1.model.input.ProdutoDTOInput;
import com.idaltchion.ifxfood.api.v1.model.input.ProdutoFotoInput;
import com.idaltchion.ifxfood.api.v1.openapi.controller.RestauranteProdutoControllerOpenAPI;
import com.idaltchion.ifxfood.domain.exception.EntidadeNaoEncontradaException;
import com.idaltchion.ifxfood.domain.model.FotoProduto;
import com.idaltchion.ifxfood.domain.model.Produto;
import com.idaltchion.ifxfood.domain.model.Restaurante;
import com.idaltchion.ifxfood.domain.repository.ProdutoRepository;
import com.idaltchion.ifxfood.domain.service.CadastroProdutoService;
import com.idaltchion.ifxfood.domain.service.CadastroRestauranteService;
import com.idaltchion.ifxfood.domain.service.CatalogoFotoProdutoService;
import com.idaltchion.ifxfood.domain.service.FotoProdutoStorageService;
import com.idaltchion.ifxfood.domain.service.FotoProdutoStorageService.FotoRecuperada;

@RestController
@RequestMapping("/v1/restaurantes/{restauranteId}/produtos")
public class RestauranteProdutoController implements RestauranteProdutoControllerOpenAPI {
	
	@Autowired
	private CadastroRestauranteService restauranteService;

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ProdutoDTOAssembler produtoDTOAssembler;
	
	@Autowired
	private ProdutoDTODisassembler produtoDTODisassembler;

	@Autowired
	private CadastroProdutoService produtoService;
	
	@Autowired
	private FotoProdutoDTOAssembler fotoProdutoDTOAssembler;
	@Autowired
	private CatalogoFotoProdutoService fotoProdutoService;
	
	@Autowired
	private FotoProdutoStorageService fotoStorageService;
	
	@Autowired
	private IfxLinks ifxLinks;
	
	@GetMapping
	public CollectionModel<ProdutoDTO> listar(@PathVariable Long restauranteId, 
			@RequestParam(required = false, defaultValue = "false") Boolean exibirInativos) {
		Restaurante restaurante = restauranteService.buscar(restauranteId);
		List<Produto> produtos = null;
		if (exibirInativos) {
			produtos = produtoRepository.findAllByRestaurante(restaurante);
		} else {
			produtos = produtoRepository.findAtivosByRestaurante(restaurante);
		}
		return produtoDTOAssembler.toCollectionModel(produtos)
				.add(ifxLinks.linkToProdutos(restauranteId, IanaLinkRelations.SELF_VALUE));
	}
	
	@GetMapping("/{produtoId}")
	public ProdutoDTO buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
		Produto produto = produtoService.buscar(produtoId, restauranteId);
		return produtoDTOAssembler.toModelWithCollectionRel(produto);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProdutoDTO adicionar(@RequestBody @Valid ProdutoDTOInput produtoInput, @PathVariable Long restauranteId) {
		Restaurante restaurante = restauranteService.buscar(restauranteId);
		Produto produto = produtoDTODisassembler.toObjectModel(produtoInput);
		produto.setRestaurante(restaurante);
		return produtoDTOAssembler.toModel(produtoService.salvar(produto));
	}
	
	@PutMapping("/{produtoId}")
	public ProdutoDTO atualizar(@PathVariable Long produtoId, @PathVariable Long restauranteId, @RequestBody @Valid ProdutoDTOInput produtoInput) {
		Produto produtoAtual = produtoService.buscar(produtoId, restauranteId);
		produtoDTODisassembler.copyToDomainObject(produtoInput, produtoAtual);
		produtoAtual = produtoService.salvar(produtoAtual);
		return produtoDTOAssembler.toModel(produtoAtual);
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
		
		return fotoProdutoDTOAssembler.toModel(fotoSalva);
	}
	
	@GetMapping(path = "/{produtoId}/foto", produces = MediaType.APPLICATION_JSON_VALUE)
	public FotoProdutoDTO buscarFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
		//TODO: Tratar a exception quando o 'Accept' nao existe no cabecalho da requisicao
		FotoProduto foto = fotoProdutoService.buscar(restauranteId, produtoId);
		return fotoProdutoDTOAssembler.toModel(foto);
	}
	
	@GetMapping(path = "/{produtoId}/foto")
	public ResponseEntity<?> servirFoto(
			@PathVariable Long restauranteId, 
			@PathVariable Long produtoId,
			@RequestHeader(name = "accept") String acceptHeader) throws HttpMediaTypeNotAcceptableException {
		try {
			FotoProduto foto = fotoProdutoService.buscar(restauranteId, produtoId);
			FotoRecuperada fotoRecuperada = fotoStorageService.recuperar(foto.getNomeArquivo());
			
			MediaType mediaTypeFoto = MediaType.parseMediaType(foto.getContentType());
			List<MediaType> mediaTypesAceitas = MediaType.parseMediaTypes(acceptHeader);
			
			verificarCompatibilidadeMediaType(mediaTypeFoto, mediaTypesAceitas);
			
			/* 
			 * disponibiliza o arquivo para o consumidor, caso o serviço de foto esteja definindo em disco local
			 */
			if (fotoRecuperada.hasInputStream()) {
				return ResponseEntity
						.ok()
						.contentType(mediaTypeFoto)
						.body(new InputStreamResource(fotoRecuperada.getInputStream()));				
			} else {
				/* 
				 * disponibiliza a url para o consumidor, caso o serviço de foto esteja definindo na Amazon S3
				 */
				return ResponseEntity
						.status(HttpStatus.FOUND)
						.header(HttpHeaders.LOCATION, fotoRecuperada.getUrl())
						.build();
			}
		}
		catch (EntidadeNaoEncontradaException e){
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("{produtoId}/foto")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void removerFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
		fotoProdutoService.excluir(restauranteId, produtoId);
	}

	private void verificarCompatibilidadeMediaType(MediaType mediaTypeFoto, List<MediaType> mediaTypesAceitas) 
			throws HttpMediaTypeNotAcceptableException {
		boolean compativel = mediaTypesAceitas.stream()
				.anyMatch(mediaTypeAceita -> mediaTypeAceita.isCompatibleWith(mediaTypeFoto));
		
		if (!compativel) {
			throw new HttpMediaTypeNotAcceptableException(mediaTypesAceitas);
		}
	}
	
}
