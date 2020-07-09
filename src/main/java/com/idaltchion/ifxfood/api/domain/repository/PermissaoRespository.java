package com.idaltchion.ifxfood.api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.idaltchion.ifxfood.api.domain.model.Permissao;

@Repository
public interface PermissaoRespository extends JpaRepository<Permissao, Long> {
	
}
