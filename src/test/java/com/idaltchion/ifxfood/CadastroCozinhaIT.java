package com.idaltchion.ifxfood;

import static org.assertj.core.api.Assertions.assertThat;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import com.idaltchion.ifxfood.domain.exception.CozinhaNaoEncontradaException;
import com.idaltchion.ifxfood.domain.exception.EntidadeEmUsoException;
import com.idaltchion.ifxfood.domain.model.Cozinha;
import com.idaltchion.ifxfood.domain.service.CadastroCozinhaService;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CadastroCozinhaIT {

	@Autowired
	private CadastroCozinhaService cozinhaService;
	
	@LocalServerPort
	private int port;
	
	@Test
	public void shoulPass_whenCozinhaAndIdIsNotNull() {
		// 1.cenario
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome("Chinesa");
		
		// 2. acao
		novaCozinha = cozinhaService.salvar(novaCozinha);
		
		// 3. validacao
		assertThat(novaCozinha).isNotNull();
		assertThat(novaCozinha.getId()).isNotNull();
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void shouldFail_whenNameOfCozinhaIsBlankOrNull() {
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome(null);
		
		novaCozinha = cozinhaService.salvar(novaCozinha);
	}
	
	@Test(expected = EntidadeEmUsoException.class)
	public void shoulFail_whenCozinhaThatIsInUseIsRemoved() {
		cozinhaService.excluir(1L);
	}
	
	@Test(expected = CozinhaNaoEncontradaException.class)
	public void shoulFail_whenCozinhaThatNotExistIsRemoved() {
		cozinhaService.excluir(99999L);
	}
	
	@Test
	public void shouldReturnHttpStatus200_whenGetCozinhas() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		
		RestAssured.given() 			//1.cenario
			.basePath("/cozinhas")
			.port(port)
			.accept(ContentType.JSON)
		.when()							// 2.acao
			.get()
		.then()							// 3.validacao
			.statusCode(HttpStatus.OK.value());
	}
	
}
