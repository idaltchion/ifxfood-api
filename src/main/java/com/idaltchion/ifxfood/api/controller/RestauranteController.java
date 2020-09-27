package com.idaltchion.ifxfood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.idaltchion.ifxfood.api.domain.exception.EntidadeNaoEncontradaException;
import com.idaltchion.ifxfood.api.domain.exception.NegocioException;
import com.idaltchion.ifxfood.api.domain.model.Restaurante;
import com.idaltchion.ifxfood.api.domain.repository.RestauranteRepository;
import com.idaltchion.ifxfood.api.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CadastroRestauranteService cadastroRestauranteService;
	
	@GetMapping
	public List<Restaurante> listar() {
		return restauranteRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Restaurante buscar(@PathVariable Long id) {
		return cadastroRestauranteService.buscar(id);
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Restaurante adicionar(@RequestBody Restaurante restaurante) {
		try {
			return cadastroRestauranteService.salvar(restaurante);
		}
		catch(EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	public Restaurante atualizar(@PathVariable Long id, @RequestBody Restaurante restaurante) {
		Restaurante restauranteAtual = cadastroRestauranteService.buscar(id);
		BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formasPagmento", "endereco", "dataCadastro", "produtos");
		try {
			return cadastroRestauranteService.salvar(restauranteAtual);			
		}
		catch(EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	@PatchMapping("/{id}")
	public Restaurante atualizarParcial(@PathVariable Long id, @RequestBody Map<String, Object> campos) {
		Restaurante restauranteAtual = cadastroRestauranteService.buscar(id);		
		merge(campos, restauranteAtual);
		return atualizar(id, restauranteAtual);
	}

	/* Reflections - maneira de inspecionar objetos para que possamos fazer alguma manipulacao no mesmo em tempo de execucao */
	private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino) {
		//Converte os tipos dos campos em dadosOrigem para os tipos existentes na classe Restaurante
		ObjectMapper objectMapper = new ObjectMapper();
		Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);
		dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
			// Procura na classe Restaurante um atributo que possui um nome que vem da variavel 'nomePropriedade'
			Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
			//Permite acessar os atributos que estão como privados da classe Restaurante
			field.setAccessible(true);
			Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
			ReflectionUtils.setField(field, restauranteDestino, novoValor);
		});
	}
	
	@GetMapping("/com-frete-gratis")
	public List<Restaurante> restauranteComFreteGratis(String nome) {
		return restauranteRepository.findComFreteGratis(nome);
	}
	
	@GetMapping("/buscar-primeiro")
	public Optional<Restaurante> buscarPrimeiro() {
		return restauranteRepository.buscarPrimeiroRegistro();
	}
	
}
