package com.idaltchion.ifxfood;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

import javax.validation.ConstraintViolationException;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.idaltchion.ifxfood.domain.exception.CozinhaNaoEncontradaException;
import com.idaltchion.ifxfood.domain.model.Cozinha;
import com.idaltchion.ifxfood.domain.repository.CozinhaRepository;
import com.idaltchion.ifxfood.domain.service.CadastroCozinhaService;
import com.idaltchion.ifxfood.util.DatabaseCleaner;
import com.idaltchion.ifxfood.util.ResourceUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(value = "/application-test.properties")
public class CadastroCozinhaIT {

	private static final int COZINHA_ID_NOT_EXIST = 999;
	
	private Cozinha cozinhaAmericana;
	
	private String jsonCozinhaArgentina;
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private CadastroCozinhaService cozinhaService;
	
	@Autowired
	private DatabaseCleaner databaseCleaner;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Before
	public void setUpBeforeTests() {
		jsonCozinhaArgentina = ResourceUtils.getContentFromResource("/json/cozinha-argentina.json");
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/cozinhas";
		
		databaseCleaner.clearTables();
		prepararDados();
		
	}
	
	private void prepararDados() {
		Cozinha cozinha1 = new Cozinha();
		cozinha1.setNome("Brasileira");
		cozinhaRepository.save(cozinha1);
		
		Cozinha cozinha2 = new Cozinha();
		cozinha2.setNome("Americana");
		cozinhaRepository.save(cozinha2);
		cozinhaAmericana = cozinha2;
	}
	
	@Test
	public void shouldPass_whenCozinhaAndIdIsNotNull() {
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
	
//	@Test(expected = EntidadeEmUsoException.class)
//	public void shoulFail_whenCozinhaThatIsInUseIsRemoved() {
//		cozinhaService.excluir(1L);
//	}
	
	@Test(expected = CozinhaNaoEncontradaException.class)
	public void shouldFail_whenCozinhaThatNotExistIsRemoved() {
		cozinhaService.excluir(Long.valueOf(COZINHA_ID_NOT_EXIST));
	}
	
	@Test
	public void shouldReturnHttpStatus200_whenGetCozinhas() {		
		RestAssured.given() 			//1.cenario
			.accept(ContentType.JSON)
		.when()							// 2.acao
			.get()
		.then()							// 3.validacao
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void shouldFail_whenCozinhaBrasileiraNotFound() {		
		RestAssured.given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("nome", Matchers.hasItem("Brasileira"));
	}
	
	@Test
	public void shouldReturnStatusCode200AndBodyCorrectly_whenCozinhaExists() {
		RestAssured.given()
			.accept(ContentType.JSON)
			.pathParam("id", cozinhaAmericana.getId())
		.when()
			.get("/{id}")
		.then()
			.assertThat().statusCode(HttpStatus.OK.value())
			.body("nome", equalTo(cozinhaAmericana.getNome()));
	}
	
	@Test
	public void shouldReturnStatusCode404_whenCozinhaNotExists() {
		RestAssured.given()
			.accept(ContentType.JSON)
			.pathParam("id", COZINHA_ID_NOT_EXIST)
		.when()
			.get("{id}")
		.then()
			.assertThat().statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	@Test
	public void shouldReturnStatusCode201_whenNewCozinhaIsAdd() {
		System.out.println(jsonCozinhaArgentina);
		RestAssured.given()
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
			.body(jsonCozinhaArgentina)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	
	
	
}
