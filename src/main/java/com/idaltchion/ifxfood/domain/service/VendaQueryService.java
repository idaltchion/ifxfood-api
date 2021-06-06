package com.idaltchion.ifxfood.domain.service;

import java.util.List;

import com.idaltchion.ifxfood.domain.filter.VendaDiariaFilter;
import com.idaltchion.ifxfood.domain.model.VendaDiaria;

public interface VendaQueryService {

	List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, String offsetTime);
	
}
