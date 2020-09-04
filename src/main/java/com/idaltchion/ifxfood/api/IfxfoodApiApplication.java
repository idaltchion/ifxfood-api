package com.idaltchion.ifxfood.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.idaltchion.ifxfood.api.infrastructure.repository.CustomJpaRepositoryImpl;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class IfxfoodApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(IfxfoodApiApplication.class, args);
	}

}
