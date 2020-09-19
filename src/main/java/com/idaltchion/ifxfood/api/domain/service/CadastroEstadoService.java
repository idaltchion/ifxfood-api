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

	private static final String MSG_ESTADO_EM_USO = "Estado com codigo %d nao pode ser removido pois esta em uso";
	private static final String MSG_ESTADO_NAO_ENCONTRADO = "Nao existe Estado cadastrado com codigo %d";
	
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
					String.format(MSG_ESTADO_NAO_ENCONTRADO, codigo));			
		}
		catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(MSG_ESTADO_EM_USO, codigo));
		}
	}
	
	public Estado buscar(Long id) {
		return estadoRepository.findById(id).orElseThrow(
			() -> new EntidadeNaoEncontradaException(String.format(MSG_ESTADO_NAO_ENCONTRADO, id)));
	}
	
}
