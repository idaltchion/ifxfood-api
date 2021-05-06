package com.idaltchion.ifxfood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.idaltchion.ifxfood.domain.exception.PermissaoNaoEncontradaException;
import com.idaltchion.ifxfood.domain.model.Grupo;
import com.idaltchion.ifxfood.domain.model.Permissao;
import com.idaltchion.ifxfood.domain.repository.PermissaoRepository;

@Service
public class CadastroPermissaoService {

	@Autowired
	PermissaoRepository permissaoRepository;

	public Permissao buscar(Long id) {
		return permissaoRepository.findById(id).orElseThrow(
				() -> new PermissaoNaoEncontradaException(id));
	}

	@Transactional
	public boolean associar(Grupo grupo, Permissao permissao) {
		return grupo.adicionarPermissao(permissao);
	}

	@Transactional
	public boolean desassociar(Grupo grupo, Permissao permissao) {
		return grupo.removerPermissao(permissao);
	}

}
