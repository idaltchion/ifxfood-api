package com.idaltchion.ifxfood.core.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*
		 * 1: permite somente autenticacao basica, ou seja, desabilita o formLogin
		 * 2: autoriza requests para o endpoint sem passar user/pwd
		 * 3: para os demais endpoints, é necessario estar autenticado para poder fazer as requests
		 * 4: nao cria um cookie na request, ou seja, nao mantem o estado da sessao. Logo, sempre deve passar user/pwd na request
		 * 5: TODO: estudar csrf
		 */
		http.httpBasic() //1
			.and()
				.authorizeRequests()
					.antMatchers("/v1/estados/**").permitAll() //2
					.anyRequest().authenticated() //3
			.and()
				.sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS) //4
			.and()
				.csrf().disable(); //5
	}
	
}
