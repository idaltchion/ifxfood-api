package com.idaltchion.ifxfood.api.v1.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
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

import com.idaltchion.ifxfood.api.v1.assembler.CozinhaDTOAssembler;
import com.idaltchion.ifxfood.api.v1.assembler.CozinhaDTODisassembler;
import com.idaltchion.ifxfood.api.v1.model.CozinhaDTO;
import com.idaltchion.ifxfood.api.v1.model.input.CozinhaDTOInput;
import com.idaltchion.ifxfood.api.v1.openapi.controller.CozinhaControllerOpenAPI;
import com.idaltchion.ifxfood.domain.model.Cozinha;
import com.idaltchion.ifxfood.domain.repository.CozinhaRepository;
import com.idaltchion.ifxfood.domain.service.CadastroCozinhaService;

/*
 * produces: suporta no Accept (header) tanto application/json quanto application/xml, o default, mesmo sem especificar, é JSON
 * entretanto, deve adicionar no pom.xml a dependencia jackson-dataformat
 */
@RestController
@RequestMapping(value = "/v1/cozinhas", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class CozinhaController implements CozinhaControllerOpenAPI {

	private static final Logger logger = LoggerFactory.getLogger(CozinhaController.class);
	
	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Autowired
	private CadastroCozinhaService cadastroCozinhaService;
	
	@Autowired
	private CozinhaDTOAssembler cozinhaDTOAssembler;
	
	@Autowired
	private CozinhaDTODisassembler cozinhaDTODisassembler;

	@Autowired
	private PagedResourcesAssembler<Cozinha> pagedResourceAssembler;
	
	@GetMapping
	public PagedModel<CozinhaDTO> listar(Pageable pageable) {
		Page<Cozinha> cozinhasPage = cozinhaRepository.findAll(pageable);
//		List<CozinhaDTO> cozinhasDTO = cozinhaDTOAssembler.toCollectionModel(cozinhasPage.getContent());
//		Page<CozinhaDTO> cozinhasDTOPage = new PageImpl<CozinhaDTO>(cozinhasDTO, pageable, cozinhasPage.getTotalElements());

		/*
		 * adicionado e mantido essa exception somente para fins didáticos para geração de logs de erro
		if (true) {
			throw new RuntimeException("Teste de logger de exception... ");
		}
		*/
		
		PagedModel<CozinhaDTO> cozinhasPagedModel = pagedResourceAssembler.toModel(cozinhasPage, cozinhaDTOAssembler);
		logger.info("Listando cozinhas... Total encontrado: {}", cozinhasPagedModel.getContent().size());
		return cozinhasPagedModel;
	}

	@GetMapping("/{id}")
	public CozinhaDTO buscar(@PathVariable Long id) {
		return cozinhaDTOAssembler.toModelWithCollectionRel(cadastroCozinhaService.buscarOuFalhar(id));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CozinhaDTO adicionar(@RequestBody @Valid CozinhaDTOInput cozinhaInput) {
		Cozinha cozinha = cozinhaDTODisassembler.toDomainObject(cozinhaInput);
		return cozinhaDTOAssembler.toModel(cadastroCozinhaService.salvar(cozinha));
	}

	@PutMapping("/{id}")
	public CozinhaDTO atualizar(@PathVariable Long id, @RequestBody CozinhaDTOInput cozinhaInput) {
		Cozinha cozinhaAtual = cadastroCozinhaService.buscarOuFalhar(id);
		cozinhaDTODisassembler.copyToDomainObject(cozinhaInput, cozinhaAtual);
		return cozinhaDTOAssembler.toModel(cadastroCozinhaService.salvar(cozinhaAtual));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		cadastroCozinhaService.excluir(id);
	}

	@GetMapping("/buscar-primeira")
	public Optional<Cozinha> buscarPrimeira() {
		return cozinhaRepository.buscarPrimeiroRegistro();
	}
}
