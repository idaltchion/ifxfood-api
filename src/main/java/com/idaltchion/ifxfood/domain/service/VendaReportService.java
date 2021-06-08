package com.idaltchion.ifxfood.domain.service;

import com.idaltchion.ifxfood.domain.filter.VendaDiariaFilter;

public interface VendaReportService {

	byte[] emitirVendasDiarias(VendaDiariaFilter filtro, String offsetTime);

}
