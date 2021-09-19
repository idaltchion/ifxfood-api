package com.idaltchion.ifxfood.core.openapi;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import org.apache.http.HttpStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.classmate.TypeResolver;
import com.idaltchion.ifxfood.api.exceptionhandler.Problem;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RepresentationBuilder;
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
		var typeResolver = new TypeResolver();
		
		return new Docket(DocumentationType.OAS_30)
			.select()
				.apis(RequestHandlerSelectors.basePackage("com.idaltchion.ifxfood.api"))
				.build()
			.useDefaultResponseMessages(false)
			.globalResponses(HttpMethod.GET, globalGetResponseMessages())
			.globalResponses(HttpMethod.POST, globalPostResponseMessages())
			.globalResponses(HttpMethod.DELETE, globalDeleteResponseMessages())
			.globalResponses(HttpMethod.PUT, globalPutResponseMessages())
			.additionalModels(typeResolver.resolve(Problem.class))
			.apiInfo(apiInfo())
			.tags(tags()[0], tags());
	}
	
	private List<Response> globalGetResponseMessages() {
		return Arrays.asList(
			new ResponseBuilder()
				.code(String.valueOf(HttpStatus.SC_INTERNAL_SERVER_ERROR))
				.description("Internal Server Error")
				.representation(MediaType.APPLICATION_JSON).apply(representationBuilderProblem())
				.build(),
			responseMessage(HttpStatus.SC_NOT_ACCEPTABLE, "Not Acceptable")
		);
	}
	
	private List<Response> globalPostResponseMessages() {
		return Arrays.asList(
			new ResponseBuilder()
				.code(String.valueOf(HttpStatus.SC_BAD_REQUEST))
				.description("Bad Request")
				.representation(MediaType.APPLICATION_JSON).apply(representationBuilderProblem())
				.build(),
			new ResponseBuilder()
				.code(String.valueOf(HttpStatus.SC_INTERNAL_SERVER_ERROR))
				.description("Internal Server Error")
				.representation(MediaType.APPLICATION_JSON).apply(representationBuilderProblem())
				.build(),
			responseMessage(HttpStatus.SC_NOT_ACCEPTABLE, "Not Acceptable"),
			responseMessage(HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE, "Unsupported Media Type")
		);
	}
	
	private List<Response> globalDeleteResponseMessages() {
		return Arrays.asList(
			new ResponseBuilder()
				.code(String.valueOf(HttpStatus.SC_BAD_REQUEST))
				.description("Bad Request")
				.representation(MediaType.APPLICATION_JSON).apply(representationBuilderProblem())
				.build(),
			new ResponseBuilder()
				.code(String.valueOf(HttpStatus.SC_INTERNAL_SERVER_ERROR))
				.description("Internal Server Error")
				.representation(MediaType.APPLICATION_JSON).apply(representationBuilderProblem())
				.build()
		);
	}
	
	private List<Response> globalPutResponseMessages() {
		return Arrays.asList(
			new ResponseBuilder()
				.code(String.valueOf(HttpStatus.SC_BAD_REQUEST))
				.description("Bad Request")
				.representation(MediaType.APPLICATION_JSON).apply(representationBuilderProblem())
				.build(),
			new ResponseBuilder()
				.code(String.valueOf(HttpStatus.SC_INTERNAL_SERVER_ERROR))
				.description("Internal Server Error")
				.representation(MediaType.APPLICATION_JSON).apply(representationBuilderProblem())
				.build(),
			responseMessage(HttpStatus.SC_NOT_ACCEPTABLE, "Not Acceptable"),
			responseMessage(HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE, "Unsupported Media Type")
		);
	}

	private Response responseMessage(int httpStatusCode, String httpMessage) {
		return new ResponseBuilder()
			.code(String.valueOf(httpStatusCode))
			.description(httpMessage)
			.build();
	}
	
	/*
	 * Método que linka a representacao da classe Problem (na resposta do swagger) com o verbo http que o chama  
	 */
	private Consumer<RepresentationBuilder> representationBuilderProblem() {
		return r -> r.model(
				m -> m.name("Problem").referenceModel(
						ref -> ref.key(
								k -> k.qualifiedModelName(
										q -> q.name("Problem").namespace("com.idaltchion.ifxfood.api.exceptionhandler")
								)
						)
					)
				);
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
