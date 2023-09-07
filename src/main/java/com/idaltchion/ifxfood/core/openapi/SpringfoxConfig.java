package com.idaltchion.ifxfood.core.openapi;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import org.apache.http.HttpStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.classmate.TypeResolver;
import com.idaltchion.ifxfood.api.exceptionhandler.Problem;
import com.idaltchion.ifxfood.api.v1.model.CidadeDTO;
import com.idaltchion.ifxfood.api.v1.model.CozinhaDTO;
import com.idaltchion.ifxfood.api.v1.model.EstadoDTO;
import com.idaltchion.ifxfood.api.v1.model.FormaPagamentoDTO;
import com.idaltchion.ifxfood.api.v1.model.GrupoDTO;
import com.idaltchion.ifxfood.api.v1.model.PedidoResumoDTO;
import com.idaltchion.ifxfood.api.v1.model.ProdutoDTO;
import com.idaltchion.ifxfood.api.v1.model.RestauranteTaxaFreteDTO;
import com.idaltchion.ifxfood.api.v1.model.UsuarioDTO;
import com.idaltchion.ifxfood.api.v1.openapi.model.CidadeDTOOpenAPI;
import com.idaltchion.ifxfood.api.v1.openapi.model.CozinhaDTOOpenAPI;
import com.idaltchion.ifxfood.api.v1.openapi.model.EstadoDTOOpenAPI;
import com.idaltchion.ifxfood.api.v1.openapi.model.FormaPagamentoDTOOpenAPI;
import com.idaltchion.ifxfood.api.v1.openapi.model.GrupoDTOOpenAPI;
import com.idaltchion.ifxfood.api.v1.openapi.model.LinksModelOpenAPI;
import com.idaltchion.ifxfood.api.v1.openapi.model.PageableModelOpenAPI;
import com.idaltchion.ifxfood.api.v1.openapi.model.PedidoResumoDTOOpenAPI;
import com.idaltchion.ifxfood.api.v1.openapi.model.ProdutoDTOOpenAPI;
import com.idaltchion.ifxfood.api.v1.openapi.model.RestauranteDTOOpenAPI;
import com.idaltchion.ifxfood.api.v1.openapi.model.UsuarioDTOOpenAPI;
import com.idaltchion.ifxfood.api.v2.model.EstadoDTOV2;
import com.idaltchion.ifxfood.api.v2.openapi.model.EstadoDTOV2OpenAPI;
import com.idaltchion.ifxfood.domain.filter.PedidoFilter;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RepresentationBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.schema.AlternateTypeRules;
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

	/*
	 * V1 session
	 */
	@Bean
	public Docket apiDocketV1() {
		var typeResolver = new TypeResolver();
		
		return new Docket(DocumentationType.OAS_30)
			.groupName("v1")
			.select()
				.apis(RequestHandlerSelectors.basePackage("com.idaltchion.ifxfood.api.v1"))
				//TODO: ajustar doc para suportar Path (/v1) + Media Type (cidades)
				//TODO: efetuar procedimento de depreciação da API
				.paths(PathSelectors.ant("/v1/**").or(PathSelectors.ant("/cidades/**")))
				.build()
			.useDefaultResponseMessages(false)
			.globalResponses(HttpMethod.GET, globalGetResponseMessages())
			.globalResponses(HttpMethod.POST, globalPostResponseMessages())
			.globalResponses(HttpMethod.DELETE, globalDeleteResponseMessages())
			.globalResponses(HttpMethod.PUT, globalPutResponseMessages())
			.additionalModels(
					typeResolver.resolve(Problem.class), 
					typeResolver.resolve(PedidoFilter.class))
			.alternateTypeRules(
					AlternateTypeRules.newRule(
							typeResolver.resolve(PagedModel.class, PedidoResumoDTO.class), PedidoResumoDTOOpenAPI.class),
					AlternateTypeRules.newRule(
							typeResolver.resolve(PagedModel.class, CozinhaDTO.class), CozinhaDTOOpenAPI.class),
					AlternateTypeRules.newRule(
							typeResolver.resolve(CollectionModel.class, CidadeDTO.class), CidadeDTOOpenAPI.class),
					AlternateTypeRules.newRule(
							typeResolver.resolve(CollectionModel.class, EstadoDTO.class), EstadoDTOOpenAPI.class),
					AlternateTypeRules.newRule(
							typeResolver.resolve(CollectionModel.class, FormaPagamentoDTO.class), FormaPagamentoDTOOpenAPI.class),
					AlternateTypeRules.newRule(
							typeResolver.resolve(CollectionModel.class, GrupoDTO.class), GrupoDTOOpenAPI.class),
					AlternateTypeRules.newRule(
							typeResolver.resolve(CollectionModel.class, ProdutoDTO.class), ProdutoDTOOpenAPI.class),
					AlternateTypeRules.newRule(
							typeResolver.resolve(CollectionModel.class, UsuarioDTO.class), UsuarioDTOOpenAPI.class),
					AlternateTypeRules.newRule(
							typeResolver.resolve(CollectionModel.class, RestauranteTaxaFreteDTO.class), RestauranteDTOOpenAPI.class))
			.directModelSubstitute(Pageable.class, PageableModelOpenAPI.class)
			.directModelSubstitute(Links.class, LinksModelOpenAPI.class)
			.apiInfo(apiInfoV1())
			.tags(tagsV1()[0], tagsV1());
	}
	
	private ApiInfo apiInfoV1() {
		return new ApiInfoBuilder()
				.version("1.0.0")
				.title("Ifxfood API")
				.description("API para delivery de refeições")
				.contact(new Contact("Idaltchion Siegel", "https://www.linkedin.com/in/idaltchion", ""))
				.build();
	}
	
	private Tag[] tagsV1() {
		return new Tag[] {
			new Tag("Cidade", "Gerencimento de Cidades"),
			new Tag("Cozinha", "Gerenciamento de Cozinhas"),
			new Tag("Estado", "Gerenciamento de Estados"),
			new Tag("Estatistica", "Consultas estatísticas"),
			new Tag("Forma de Pagamento", "Gerenciamento de Formas de Pagamento"),
			new Tag("Grupo", "Gerenciamento de Grupos"),
			new Tag("Grupo Permissao", "Gerenciamento das Permissões de Grupos"),
			new Tag("Pedido", "Gerenciamento de Pedidos"),
			new Tag("Restaurante", "Gerenciamento de Restaurantes")
		};
	}
	
	
	/*
	 * V2 session
	 */
	@Bean
	public Docket apiDocketV2() {
		var typeResolver = new TypeResolver();
		
		return new Docket(DocumentationType.OAS_30)
			.groupName("v2")
			.select()
				.apis(RequestHandlerSelectors.basePackage("com.idaltchion.ifxfood.api.v2"))
				//TODO: ajustar doc para suportar Path (/v2) + Media Type (cidades)
				.paths(PathSelectors.regex("^/(v2|cidades).*$"))
				.build()
			.useDefaultResponseMessages(false)
			.globalResponses(HttpMethod.GET, globalGetResponseMessages())
			.globalResponses(HttpMethod.POST, globalPostResponseMessages())
			.globalResponses(HttpMethod.DELETE, globalDeleteResponseMessages())
			.globalResponses(HttpMethod.PUT, globalPutResponseMessages())
			.additionalModels(
					typeResolver.resolve(Problem.class), 
					typeResolver.resolve(PedidoFilter.class))
			.alternateTypeRules(
					AlternateTypeRules.newRule(
							typeResolver.resolve(CollectionModel.class, EstadoDTOV2.class), EstadoDTOV2OpenAPI.class)
					)
			.directModelSubstitute(Pageable.class, PageableModelOpenAPI.class)
			.directModelSubstitute(Links.class, LinksModelOpenAPI.class)
			.apiInfo(apiInfoV2())
			.tags(tagsV2()[0], tagsV2());
	}
	
	private ApiInfo apiInfoV2() {
		return new ApiInfoBuilder()
				.version("2.0.0")
				.title("Ifxfood API")
				.description("API para delivery de refeições")
				.contact(new Contact("Idaltchion Siegel", "https://www.linkedin.com/in/idaltchion", ""))
				.build();
	}
	
	private Tag[] tagsV2() {
		return new Tag[] {
			new Tag("Cidades", "Gerenciamento de Cidades"), 
			new Tag("Estados", "Gerenciamento de Estados")
		};
	}
	
	
	/*
	 * General session
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("index.html")
			.addResourceLocations("classpath:META-INF/resources/webjars/springfox-swagger-ui/**");
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
	
}
