package com.idaltchion.ifxfood.domain.filter;

import java.time.OffsetDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel
@Getter
@Setter
public class PedidoFilter {

	@ApiModelProperty(value = "Codigo do cliente para filtro da pesquisa", example = "1")
	private Long clienteId;
	
	@ApiModelProperty(value = "Codigo do restaurante", example = "3")
	private Long restauranteId;
	
	@ApiModelProperty(value = "Data/hora inicial de criação do pedido para filtro da pesquisa", example = "2021-11-07T08:30:00.000")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private OffsetDateTime dataCriacaoInicio;
	
	@ApiModelProperty(value = "Data/hora final de criação do pedido para filtro da pesquisa", example = "2021-11-15T21:00:00.000")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private OffsetDateTime dataCriacaoFim;
	
}
