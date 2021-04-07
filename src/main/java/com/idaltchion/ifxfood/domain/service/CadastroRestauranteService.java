package com.idaltchion.ifxfood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.idaltchion.ifxfood.domain.exception.RestauranteNaoEncontradoException;
import com.idaltchion.ifxfood.domain.model.Cidade;
import com.idaltchion.ifxfood.domain.model.Cozinha;
import com.idaltchion.ifxfood.domain.model.Restaurante;
import com.idaltchion.ifxfood.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CadastroCozinhaService cadastroCozinhaService;
	
	@Autowired
	private CadastroCidadeService cadastroCidadeService;
	
	@Transactional
	public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		Long cidadeId = restaurante.getEndereco().getCidade().getId();

		Cozinha cozinha = cadastroCozinhaService.buscarOuFalhar(cozinhaId);
		Cidade cidade = cadastroCidadeService.buscar(cidadeId);
		
		restaurante.getEndereco().setCidade(cidade);
		
		restaurante.setCozinha(cozinha);
		
		return restauranteRepository.save(restaurante);
	}
	
	public Restaurante buscar(Long id) {
		return restauranteRepository.findById(id).orElseThrow(
				() -> new RestauranteNaoEncontradoException(id));
	}
	
	@Transactional
	public void ativar(Long id) {
		Restaurante restauranteAtual = buscar(id);
		restauranteAtual.ativar();
	}
	
	@Transactional
	public void inativar(Long id) {
		Restaurante restauranteAtual = buscar(id);
		restauranteAtual.inativar();
	}
	
}
