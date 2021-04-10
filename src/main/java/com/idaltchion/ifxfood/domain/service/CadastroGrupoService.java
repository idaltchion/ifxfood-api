package com.idaltchion.ifxfood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.idaltchion.ifxfood.domain.exception.EntidadeEmUsoException;
import com.idaltchion.ifxfood.domain.exception.GrupoNaoEncontradoException;
import com.idaltchion.ifxfood.domain.model.Grupo;
import com.idaltchion.ifxfood.domain.repository.GrupoRepository;

@Service
public class CadastroGrupoService {

	private static final String MSG_GRUPO_EM_USO = "Grupo com id %d nao pode ser removido pois esta em uso";

	@Autowired
	GrupoRepository grupoRepository;

	public Grupo buscar(Long id) {
		return grupoRepository.findById(id).orElseThrow(() -> new GrupoNaoEncontradoException(id));
	}

	@Transactional
	public void deletar(Long id) {
		Grupo grupo = buscar(id);
		try {
			grupoRepository.deleteById(grupo.getId());
			grupoRepository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_GRUPO_EM_USO, id));
		}
	}

	@Transactional
	public Grupo salvar(Grupo grupo) {
		return grupoRepository.saveAndFlush(grupo);
	}

}
