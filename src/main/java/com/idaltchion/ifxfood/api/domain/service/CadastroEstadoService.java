package com.idaltchion.ifxfood.api.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.idaltchion.ifxfood.api.domain.exception.EntidadeNaoEncontradaException;
import com.idaltchion.ifxfood.api.domain.model.Estado;
import com.idaltchion.ifxfood.api.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {

	@Autowired
	private EstadoRepository estadoRepository;
	
	public Estado salvar(Estado estado) {
		return estadoRepository.salvar(estado);
	}
	
	public void remover(Long codigo) {
		try {
			estadoRepository.remover(codigo);
		}
		catch(EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException (
					String.format("Nao existe estado cadastrado com codigo %d", codigo));			
		}
	}
	
}
