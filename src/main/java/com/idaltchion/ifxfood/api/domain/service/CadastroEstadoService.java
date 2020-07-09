package com.idaltchion.ifxfood.api.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.idaltchion.ifxfood.api.domain.exception.EntidadeEmUsoException;
import com.idaltchion.ifxfood.api.domain.exception.EntidadeNaoEncontradaException;
import com.idaltchion.ifxfood.api.domain.model.Estado;
import com.idaltchion.ifxfood.api.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {

	@Autowired
	private EstadoRepository estadoRepository;
	
	public Estado salvar(Estado estado) {
		return estadoRepository.save(estado);
	}
	
	public void remover(Long codigo) {
		try {
			estadoRepository.deleteById(codigo);
		}
		catch(EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException (
					String.format("Nao existe estado cadastrado com codigo %d", codigo));			
		}
		catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Estado com codigo %d nao pode ser removido pois esta em uso", codigo));
		}
	}
	
}
