package com.idaltchion.ifxfood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.idaltchion.ifxfood.domain.exception.NegocioException;
import com.idaltchion.ifxfood.domain.exception.RestauranteNaoEncontradoException;
import com.idaltchion.ifxfood.domain.model.Cidade;
import com.idaltchion.ifxfood.domain.model.Cozinha;
import com.idaltchion.ifxfood.domain.model.FormaPagamento;
import com.idaltchion.ifxfood.domain.model.Restaurante;
import com.idaltchion.ifxfood.domain.model.Usuario;
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
	public void ativarMultiplos(List<Long> restauranteIds) {
		try {
			restauranteIds.forEach(this::ativar);
		}
		catch(RestauranteNaoEncontradoException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	@Transactional
	public void inativar(Long id) {
		Restaurante restauranteAtual = buscar(id);
		restauranteAtual.inativar();
	}
	
	@Transactional
	public void inativarMultiplos(List<Long> restauranteIds) {
		try {
			restauranteIds.forEach(this::inativar);
		}
		catch(RestauranteNaoEncontradoException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@Transactional
	public void associar(Restaurante restaurante, FormaPagamento formaPagamento) {
		restaurante.adicionarFormaPagamento(formaPagamento);
	}

	@Transactional
	public void desassociar(Restaurante restaurante, FormaPagamento formaPagamento) {
		restaurante.removerFormaPagamento(formaPagamento);
	}

	@Transactional
	public void abrir(Long id) {
		Restaurante restaurante = buscar(id);
		restaurante.abrir();
	}
	
	@Transactional
	public void fechar(Long id) {
		Restaurante restaurante = buscar(id);
		restaurante.fechar();
	}

	@Transactional
	public boolean associarResponsavel(Restaurante restaurante, Usuario usuario) {
		return restaurante.adicionarResponsavel(usuario);
	}

	@Transactional
	public boolean desassociarResponsavel(Restaurante restaurante, Usuario usuario) {
		return restaurante.removerResponsavel(usuario);
	}

}
