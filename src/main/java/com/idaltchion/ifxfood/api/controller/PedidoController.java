package com.idaltchion.ifxfood.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.ImmutableMap;
import com.idaltchion.ifxfood.api.assembler.PedidoDTOAssembler;
import com.idaltchion.ifxfood.api.assembler.PedidoDTODisassembler;
import com.idaltchion.ifxfood.api.assembler.PedidoResumoDTOAssembler;
import com.idaltchion.ifxfood.api.model.PedidoDTO;
import com.idaltchion.ifxfood.api.model.PedidoResumoDTO;
import com.idaltchion.ifxfood.api.model.input.PedidoDTOInput;
import com.idaltchion.ifxfood.api.openapi.controller.PedidoControllerOpenAPI;
import com.idaltchion.ifxfood.core.data.PageWrapper;
import com.idaltchion.ifxfood.core.data.PageableTranslator;
import com.idaltchion.ifxfood.domain.filter.PedidoFilter;
import com.idaltchion.ifxfood.domain.model.Pedido;
import com.idaltchion.ifxfood.domain.model.Usuario;
import com.idaltchion.ifxfood.domain.service.CadastroPedidoService;

@RestController
@RequestMapping(path = "/pedidos", produces = MediaType.APPLICATION_JSON_VALUE)
public class PedidoController implements PedidoControllerOpenAPI {
	
	@Autowired
	CadastroPedidoService pedidoService;
	
	@Autowired
	PedidoDTOAssembler pedidoDTOAssembler;
	
	@Autowired
	PedidoDTODisassembler pedidoDTODisassembler;
	
	@Autowired
	PedidoResumoDTOAssembler pedidoResumoDTOAssembler;
	
	@Autowired
	PagedResourcesAssembler<Pedido> pagedResourceAssember;
	
	@GetMapping
	public PagedModel<PedidoResumoDTO> pesquisar(PedidoFilter filtro, @PageableDefault(size = 5) Pageable pageable) {
		Pageable pageableTraduzido = traduzirPageable(pageable);
		Page<Pedido> pedidosPage = pedidoService.listar(filtro, pageableTraduzido);
		
		/*
		 * correcao de 'bug': 
		 * - para poder repassar os nomes dos atributos da paginacao original (ao inves do traduzido) nos links
		 * - sem esse artificio, os atributos 'traduzidos', ou seja, atributos de dominio estavam sendo repassados nos links
		 * - a consequencia disso é que como os atributos de dominio nao estao na lista de 'permitidos', os links com a opcao 'sort' nao eram repassados nos links
		 */
		pedidosPage = new PageWrapper<>(pedidosPage, pageable);
		
		return pagedResourceAssember.toModel(pedidosPage, pedidoResumoDTOAssembler);
	}
	
	@GetMapping("/{codigo_pedido}")
	public PedidoDTO buscar(@PathVariable String codigo_pedido) {
		return pedidoDTOAssembler.toModelWithCollectionRel(pedidoService.buscar(codigo_pedido));
	}
	
	@PutMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PedidoDTO emitirPedido(@RequestBody @Valid PedidoDTOInput pedidoInput) {
		Pedido novoPedido = pedidoDTODisassembler.toDomain(pedidoInput);
		//TODO: alterar para o usuario que esta logado na aplicação
		novoPedido.setCliente(new Usuario());
		novoPedido.getCliente().setId(1L);
		
		novoPedido = pedidoService.emitirPedido(novoPedido);
		return pedidoDTOAssembler.toModelWithCollectionRel(novoPedido);
	}

	/* metodo que faz uma tradução das propriedades passadas pelo sort DA paginacao
	 * PARA os atributos existentes na classe de dominio (Pedido).
	 * Os atributos aqui mencionados, também são os permitidos a realizar ordenação. */
	private Pageable traduzirPageable(Pageable apiPageable) {
		var mapeamento = ImmutableMap.of(
					"codigo", "codigo",
					"status", "status",
					"dataCriacao", "dataCriacao",
					"restaurante.nome", "restaurante.nome",
					"cliente.nome", "cliente.nome"					
				);
		return PageableTranslator.translate(apiPageable, mapeamento);
	}
	
}
