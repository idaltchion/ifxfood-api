package com.idaltchion.ifxfood.api.exceptionhandler;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Problema {
	private LocalDateTime dateTime;
	private String message;
}
