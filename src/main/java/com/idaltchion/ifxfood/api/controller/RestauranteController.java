package com.idaltchion.ifxfood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.idaltchion.ifxfood.api.assembler.RestauranteApenasNomeDTOAssembler;
import com.idaltchion.ifxfood.api.assembler.RestauranteDTOAssembler;
import com.idaltchion.ifxfood.api.assembler.RestauranteDTODisassembler;
import com.idaltchion.ifxfood.api.assembler.RestauranteTaxaFreteDTOAssembler;
import com.idaltchion.ifxfood.api.model.RestauranteApenasNomeDTO;
import com.idaltchion.ifxfood.api.model.RestauranteDTO;
import com.idaltchion.ifxfood.api.model.RestauranteTaxaFreteDTO;
import com.idaltchion.ifxfood.api.model.input.RestauranteDTOInput;
import com.idaltchion.ifxfood.api.openapi.controller.RestauranteControllerOpenAPI;
import com.idaltchion.ifxfood.core.validation.ValidacaoException;
import com.idaltchion.ifxfood.domain.exception.CidadeNaoEncontradaException;
import com.idaltchion.ifxfood.domain.exception.CozinhaNaoEncontradaException;
import com.idaltchion.ifxfood.domain.exception.NegocioException;
import com.idaltchion.ifxfood.domain.model.Restaurante;
import com.idaltchion.ifxfood.domain.repository.RestauranteRepository;
import com.idaltchion.ifxfood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping(path = "/restaurantes", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteController implements RestauranteControllerOpenAPI {

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CadastroRestauranteService cadastroRestauranteService;
	
	@Autowired
	private SmartValidator validator;
	
	@Autowired
	private RestauranteDTOAssembler restauranteDTOAssembler;
	
	@Autowired
	private RestauranteTaxaFreteDTOAssembler restauranteTaxaFreteDTOAssembler;
	
	@Autowired
	private RestauranteApenasNomeDTOAssembler restauranteApenasNomeDTOAssembler;
	
	@Autowired
	private RestauranteDTODisassembler restauranteDTODisassembler;
	
	@GetMapping
	public CollectionModel<RestauranteTaxaFreteDTO> listar() {
		return restauranteTaxaFreteDTOAssembler.toCollectionModel(restauranteRepository.findAll());
	}
	
	@GetMapping(params = "projecao=apenas-nome")
	public CollectionModel<RestauranteApenasNomeDTO> listarApenasNomes() {
		return restauranteApenasNomeDTOAssembler.toCollectionModel(restauranteRepository.findAll());
	}

	@GetMapping("/{id}")
	public RestauranteDTO buscar(@PathVariable Long id) {
		Restaurante restaurante =  cadastroRestauranteService.buscar(id);
		return restauranteDTOAssembler.toModel(restaurante);
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public RestauranteDTO adicionar(@RequestBody @Valid RestauranteDTOInput restauranteInput) {
		try {
			Restaurante restaurante = restauranteDTODisassembler.toDomain(restauranteInput);
			return restauranteDTOAssembler.toModel(cadastroRestauranteService.salvar(restaurante));
		}
		catch(CozinhaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	public RestauranteDTO atualizar(@PathVariable Long id, @RequestBody @Valid RestauranteDTOInput restauranteInput) {
		try {
			Restaurante restauranteAtual = cadastroRestauranteService.buscar(id);
			restauranteDTODisassembler.copyToDomainObject(restauranteInput, restauranteAtual);
			return restauranteDTOAssembler.toModel(cadastroRestauranteService.salvar(restauranteAtual));
		}
		catch(CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	@PatchMapping("/{id}")
	public RestauranteDTO atualizarParcial(@PathVariable Long id, @RequestBody Map<String, Object> campos, 
			HttpServletRequest request) {
		
		Restaurante restauranteAtual = cadastroRestauranteService.buscar(id);		
		merge(campos, restauranteAtual, request);
		validate(restauranteAtual, "restaurante");
//		return atualizar(id, restauranteAtual); //removido temporariamente devido do DTO de Input
		return null;
	}
	
	@GetMapping("/com-frete-gratis")
	public CollectionModel<RestauranteDTO> restauranteComFreteGratis(String nome) {
		return restauranteDTOAssembler.toCollectionModel(restauranteRepository.findComFreteGratis(nome));
	}
	
	@GetMapping("/buscar-primeiro")
	public Optional<Restaurante> buscarPrimeiro() {
		return restauranteRepository.buscarPrimeiroRegistro();
	}
	
	@PutMapping("/{id}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> ativar(@PathVariable Long id) {
		cadastroRestauranteService.ativar(id); 
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/ativacoes")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void ativarMultiplos(@RequestBody List<Long> restauranteIds) {
		cadastroRestauranteService.ativarMultiplos(restauranteIds);
	}
	
	@DeleteMapping("/{id}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> inativar(@PathVariable Long id) {
		cadastroRestauranteService.inativar(id);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/ativacoes")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void inativarMultiplos(@RequestBody List<Long> restauranteIds) {
		cadastroRestauranteService.inativarMultiplos(restauranteIds);
	}
	
	@PutMapping("/{id}/abertura")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> abrir(@PathVariable Long id) {
		cadastroRestauranteService.abrir(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}/fechamento")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> fechar(@PathVariable Long id) {
		cadastroRestauranteService.fechar(id);
		return ResponseEntity.noContent().build();
	}
	
	private void validate(Restaurante restaurante, String objectName) {
		BeanPropertyBindingResult bindingresult = new BeanPropertyBindingResult(restaurante, objectName);
		validator.validate(restaurante, bindingresult);
		if (bindingresult.hasErrors()) {
			throw new ValidacaoException(bindingresult);
		}
	}

	/* Reflections - maneira de inspecionar objetos para que possamos fazer alguma manipulacao no mesmo em tempo de execucao */
	private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino, HttpServletRequest request) {
		ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(request);
		try {
			//Converte os tipos dos campos em dadosOrigem para os tipos existentes na classe Restaurante
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
			Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);
			dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
				// Procura na classe Restaurante um atributo que possui um nome que vem da variavel 'nomePropriedade'
				Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
				//Permite acessar os atributos que estão como privados da classe Restaurante
				field.setAccessible(true);
				Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
				ReflectionUtils.setField(field, restauranteDestino, novoValor);
			});
		} catch(IllegalArgumentException e) {
			Throwable rootCause = ExceptionUtils.getRootCause(e);
			throw new HttpMessageNotReadableException(e.getMessage(), rootCause, serverHttpRequest);
		}
	}
	
}
