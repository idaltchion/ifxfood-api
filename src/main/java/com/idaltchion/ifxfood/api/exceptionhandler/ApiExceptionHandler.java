package com.idaltchion.ifxfood.api.exceptionhandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.idaltchion.ifxfood.api.domain.exception.EntidadeEmUsoException;
import com.idaltchion.ifxfood.api.domain.exception.EntidadeNaoEncontradaException;
import com.idaltchion.ifxfood.api.domain.exception.NegocioException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> handlerNegocioException(NegocioException ex, WebRequest request) {
		return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> handlerEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex, WebRequest request) {
		return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<?> handlerEntidadeEmUsoException(EntidadeEmUsoException ex, WebRequest request) {
		return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		if (body == null) {
			body = Problema.builder()
					.dateTime(LocalDateTime.now())
					.message(ex.getMessage()).build();			
		} else if (body instanceof String) {
			body = Problema.builder()
					.dateTime(LocalDateTime.now())
					.message((String) body).build();
		}
		
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
	
}
