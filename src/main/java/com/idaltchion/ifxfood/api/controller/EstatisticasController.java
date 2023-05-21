package com.idaltchion.ifxfood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.idaltchion.ifxfood.api.IfxLinks;
import com.idaltchion.ifxfood.domain.filter.VendaDiariaFilter;
import com.idaltchion.ifxfood.domain.model.VendaDiaria;
import com.idaltchion.ifxfood.domain.service.VendaQueryService;
import com.idaltchion.ifxfood.domain.service.VendaReportService;

import io.swagger.annotations.Api;

@Api(tags = "Estatistica")
@RestController
@RequestMapping(path = "/estatisticas")
public class EstatisticasController {

	@Autowired
	public VendaQueryService vendaQueryService;
	
	@Autowired
	public VendaReportService vendaReportService;
	
	@Autowired
	private IfxLinks ifxLinks;
	
	private static class EstatisticasModel extends RepresentationModel<EstatisticasModel>{
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public EstatisticasModel estatisticas() {
		var estatisticasModel = new EstatisticasModel();
		estatisticasModel.add(ifxLinks.linkToEstatisticasVendasDiarias("vendas-diarias"));
		return estatisticasModel;
	}
	
	@GetMapping(path = "/vendas-diarias", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<VendaDiaria> consultaVendasDiarias(VendaDiariaFilter filtro, 
			@RequestParam(required = false, defaultValue = "+00:00") String offsetTime) {
		return vendaQueryService.consultarVendasDiarias(filtro, offsetTime);
	}
	
	@GetMapping(value = "/vendas-diarias", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> consultaVendasDiariasPdf(VendaDiariaFilter filtro, 
			@RequestParam(required = false, defaultValue = "+00:00") String offsetTime) {
		
		var bytesPdf = vendaReportService.emitirVendasDiarias(filtro, offsetTime);
		
		var headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=vendas-diarias.pdf");
		
		return ResponseEntity
				.ok()
				.contentType(MediaType.APPLICATION_PDF)
				.headers(headers)
				.body(bytesPdf);
	}
	
}
