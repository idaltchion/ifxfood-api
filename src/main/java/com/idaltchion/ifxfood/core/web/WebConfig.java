package com.idaltchion.ifxfood.core.web;

import javax.servlet.Filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOrigins("*")
			.allowedMethods("*");
	}
	
	/*
	 * Define o ContentType (header Accept) padrao da API
	 */
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//		configurer.defaultContentType(IfxMediaTypes.V2_APPLICATION_JSON);
		configurer.defaultContentType(MediaType.APPLICATION_JSON);
	}
	
	@Bean
	public Filter shallowEtagHeaderFilter() {
		return new ShallowEtagHeaderFilter();
	}
	
}
