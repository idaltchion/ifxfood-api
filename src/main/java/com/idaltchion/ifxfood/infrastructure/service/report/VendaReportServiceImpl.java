package com.idaltchion.ifxfood.infrastructure.service.report;

import java.util.HashMap;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idaltchion.ifxfood.domain.filter.VendaDiariaFilter;
import com.idaltchion.ifxfood.domain.service.VendaQueryService;
import com.idaltchion.ifxfood.domain.service.VendaReportService;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class VendaReportServiceImpl implements VendaReportService {

	@Autowired
	VendaQueryService vendaQueryService;

	@Override
	public byte[] emitirVendasDiarias(VendaDiariaFilter filtro, String offsetTime) {

		try {
			var inputStream = this.getClass().getResourceAsStream("/reports/vendas-diarias.jasper");
			var parameters = new HashMap<String, Object>();
			parameters.put("REPORT_LOCALE", new Locale("pt", "BR"));

			var vendasDiarias = vendaQueryService.consultarVendasDiarias(filtro, offsetTime);

			var dataSource = new JRBeanCollectionDataSource(vendasDiarias);

			var jasperPrint = JasperFillManager.fillReport(inputStream, parameters, dataSource);

			return JasperExportManager.exportReportToPdf(jasperPrint);
			
		} catch (Exception e) {
			throw new ReportException("Não foi possível emitir o relatório de vendas diárias", e);
		}
	}

}
