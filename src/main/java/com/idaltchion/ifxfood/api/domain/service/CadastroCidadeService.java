package com.idaltchion.ifxfood.api.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.idaltchion.ifxfood.api.domain.exception.EntidadeNaoEncontradaException;
import com.idaltchion.ifxfood.api.domain.model.Cidade;
import com.idaltchion.ifxfood.api.domain.model.Estado;
import com.idaltchion.ifxfood.api.domain.repository.CidadeRepository;

@Service
public class CadastroCidadeService {

	private static final String MSG_CIDADE_NAO_CADASTRADA = "Nao existe Cidade cadastrada com codigo %d";

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private CadastroEstadoService cadastroEstadoService;
	
	public void remover(Long id) {
		try {
			cidadeRepository.deleteById(id);			
		}
		catch(EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format(MSG_CIDADE_NAO_CADASTRADA, id));
		}
	}
	
	public Cidade salvar(Cidade cidade) {
		Long estadoId = cidade.getEstado().getId();
		Estado estado = cadastroEstadoService.buscar(estadoId);
		cidade.setEstado(estado);
		return cidadeRepository.save(cidade);
	}
	
	public Cidade buscar(Long id) {
		return cidadeRepository.findById(id).orElseThrow(
			() -> new EntidadeNaoEncontradaException(String.format(MSG_CIDADE_NAO_CADASTRADA, id)));
	}
	
}
