package com.idaltchion.ifxfood.core.openapi;

import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringfoxConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("index.html")
			.addResourceLocations("classpath:META-INF/resources/webjars/springfox-swagger-ui/**");
	}
	
	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.OAS_30)
				.select()
					.apis(RequestHandlerSelectors.basePackage("com.idaltchion.ifxfood.api"))
					.build()
				.apiInfo(apiInfo())
				.tags(tags()[0], tags())
				.useDefaultResponseMessages(false)
				.globalResponses(HttpMethod.GET, globalGetResponseMessages())
				.globalResponses(HttpMethod.POST, globalPostResponseMessages())
				.globalResponses(HttpMethod.DELETE, globalDeleteResponseMessages())
				.globalResponses(HttpMethod.PUT, globalPutResponseMessages());
	}
	
	private List<Response> globalPutResponseMessages() {
		return Arrays.asList(
				responseMessage(HttpStatus.SC_BAD_REQUEST, "Bad Request"),
				responseMessage(HttpStatus.SC_INTERNAL_SERVER_ERROR, "Internal Server Error"),
				responseMessage(HttpStatus.SC_NOT_ACCEPTABLE, "Not Acceptable"),
				responseMessage(HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE, "Unsupported Media Type")
		);
	}

	private List<Response> globalDeleteResponseMessages() {
		return Arrays.asList(
				responseMessage(HttpStatus.SC_BAD_REQUEST, "Bad Request"),
				responseMessage(HttpStatus.SC_INTERNAL_SERVER_ERROR, "Internal Server Error")
		);
	}

	private List<Response> globalPostResponseMessages() {
		return Arrays.asList(
				responseMessage(HttpStatus.SC_BAD_REQUEST, "Bad Request"),
				responseMessage(HttpStatus.SC_INTERNAL_SERVER_ERROR, "Internal Server Error"),
				responseMessage(HttpStatus.SC_NOT_ACCEPTABLE, "Not Acceptable"),
				responseMessage(HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE, "Unsupported Media Type")
		);
	}
	
	private List<Response> globalGetResponseMessages() {
		return Arrays.asList(
				responseMessage(HttpStatus.SC_INTERNAL_SERVER_ERROR, "Internal Server Error"),
				responseMessage(HttpStatus.SC_NOT_ACCEPTABLE, "Not Acceptable")
		);
	}
	
	private Response responseMessage(int httpStatusCode, String httpMessage) {
		return new ResponseBuilder()
				.code(String.valueOf(httpStatusCode))
				.description(httpMessage)
				.build();
	}

	private Tag[] tags() {
		return new Tag[] {
			new Tag("Cidade", "Gerencimento de Cidades"),
			new Tag("Cozinha", "Gerenciamento de Cozinhas"),
			new Tag("Estado", "Gerenciamento de Estados"),
			new Tag("Estatistica", "Consultas estatísticas")
		};
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Ifxfood API")
				.description("API para delivery de refeições")
				.contact(new Contact("Idaltchion Siegel", "https://www.linkedin.com/in/idaltchion", ""))
				.build();
	}
	
}
