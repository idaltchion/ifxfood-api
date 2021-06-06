package com.idaltchion.ifxfood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.idaltchion.ifxfood.domain.filter.VendaDiariaFilter;
import com.idaltchion.ifxfood.domain.model.VendaDiaria;
import com.idaltchion.ifxfood.domain.service.VendaQueryService;

@RestController
@RequestMapping(path = "/estatisticas")
public class EstatisticasController {

	@Autowired
	public VendaQueryService vendaQueryService;
	
	@GetMapping("/vendas-diarias")
	public List<VendaDiaria> consultaVendasDiarias(VendaDiariaFilter filtro, 
			@RequestParam(required = false, defaultValue = "+00:00") String offsetTime) {
		return vendaQueryService.consultarVendasDiarias(filtro, offsetTime);
	}
	
}
