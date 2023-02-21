package com.idaltchion.ifxfood.api;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.experimental.UtilityClass;

/* 
 * Classe utilitaria para add informacoes no header 
 * Inicio da implementacao de HATEOAS
 * Anotacao somente para reforcar que seja uma classe utilitaria - sem poder extender, final, etc 
*/
@UtilityClass
public class ResourceUriHelper {

	public static void addUriInResponse(Object resourceId) {
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/{id}")
				.buildAndExpand(resourceId).toUri();
		
		HttpServletResponse response = ((ServletRequestAttributes) 
				RequestContextHolder.getRequestAttributes()).getResponse();
		
		response.setHeader(HttpHeaders.LOCATION, uri.toString());
	}
	
}
