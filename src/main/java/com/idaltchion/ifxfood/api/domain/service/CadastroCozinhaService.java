package com.idaltchion.ifxfood.api.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.idaltchion.ifxfood.api.domain.exception.EntidadeEmUsoException;
import com.idaltchion.ifxfood.api.domain.exception.EntidadeNaoEncontradaException;
import com.idaltchion.ifxfood.api.domain.model.Cozinha;
import com.idaltchion.ifxfood.api.domain.repository.CozinhaRepository;

@Service
public class CadastroCozinhaService {

	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	public Cozinha salvar(Cozinha cozinha) {
		return cozinhaRepository.save(cozinha);
	}
	
	public void excluir(Long id) {
		try {
			cozinhaRepository.deleteById(id);	
		}
		catch(EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException (
				String.format("Cozinha com id %d nao encontrada", id));
		}
		catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
				String.format("Cozinha com id %d nao pode ser removida pois esta em uso", id));
		}
	}
}
