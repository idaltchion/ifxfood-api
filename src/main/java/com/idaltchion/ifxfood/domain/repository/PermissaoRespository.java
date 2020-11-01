package com.idaltchion.ifxfood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.idaltchion.ifxfood.domain.model.Permissao;

@Repository
public interface PermissaoRespository extends JpaRepository<Permissao, Long> {
	
}
