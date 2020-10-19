package com.idaltchion.ifxfood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

	MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensível"),
	RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
	ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
	VIOLACAO_REGRA_NEGOCIO("/violacao-regra-negocio", "Violação de regra de negócio"),
	PARAMETRO_INVALIDO("/parametro-invalido", "Parametro invalido"),
	ERRO_DE_SISTEMA("/erro-de-sistema", "Erro interno de sistema"), 
	DADOS_INVALIDOS("/dado-invalido", "Dado inválido");
	
	private String title;
	private String uri;
	
	ProblemType(String path, String title) {
		this.uri = "https://ifxfood.com.br" + path;
		this.title = title;
	}
}
