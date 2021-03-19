package com.idaltchion.ifxfood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.idaltchion.ifxfood.domain.exception.CidadeNaoEncontradaException;
import com.idaltchion.ifxfood.domain.model.Cidade;
import com.idaltchion.ifxfood.domain.model.Estado;
import com.idaltchion.ifxfood.domain.repository.CidadeRepository;

@Service
public class CadastroCidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private CadastroEstadoService cadastroEstadoService;
	
	@Transactional
	public void remover(Long id) {
		try {
			cidadeRepository.deleteById(id);
			cidadeRepository.flush();
		}
		catch(EmptyResultDataAccessException e) {
			throw new CidadeNaoEncontradaException(id);
		}
	}
	
	@Transactional
	public Cidade salvar(Cidade cidade) {
		Long estadoId = cidade.getEstado().getId();
		Estado estado = cadastroEstadoService.buscar(estadoId);
		cidade.setEstado(estado);
		return cidadeRepository.save(cidade);
	}
	
	public Cidade buscar(Long id) {
		return cidadeRepository.findById(id).orElseThrow(
			() -> new CidadeNaoEncontradaException(id));
	}
	
}
