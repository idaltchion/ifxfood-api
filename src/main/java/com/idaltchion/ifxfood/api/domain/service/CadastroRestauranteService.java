package com.idaltchion.ifxfood.api.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idaltchion.ifxfood.api.domain.exception.EntidadeNaoEncontradaException;
import com.idaltchion.ifxfood.api.domain.model.Cozinha;
import com.idaltchion.ifxfood.api.domain.model.Restaurante;
import com.idaltchion.ifxfood.api.domain.repository.CozinhaRepository;
import com.idaltchion.ifxfood.api.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		Cozinha cozinha = cozinhaRepository.findById(cozinhaId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(
						String.format("Nao existe cadastro de cozinha com codigo %d", cozinhaId)));
		restaurante.setCozinha(cozinha);
		return restauranteRepository.save(restaurante);
	}
}
