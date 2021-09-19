package com.idaltchion.ifxfood.core.openapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringfoxConfig implements WebMvcConfigurer {

	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.OAS_30)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.idaltchion.ifxfood.api"))
				.build()
				.apiInfo(apiInfo())
				.tags(tags()[0], tags());
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

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("index.html")
			.addResourceLocations("classpath:META-INF/resources/webjars/springfox-swagger-ui/**");
	}
	
}
