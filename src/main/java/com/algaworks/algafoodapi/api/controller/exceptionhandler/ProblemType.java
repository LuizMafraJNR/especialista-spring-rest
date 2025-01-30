package com.algaworks.algafoodapi.api.controller.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType
{
	MENSAGEM_INCOMPREENSIVEL("Mensagem incompreensível", "mensagem-incompreensivel"),
	RECURSO_NAO_ENCONTRADO("Recurso nao Encontrado", "recurso-nao-encontrado"),
	ENTIDADE_EM_USO("Entidade em uso", "entidade-em-uso"),
	ERRO_NEGOCIO("Violação de regra de negócio", "erro-negocio"),
	PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro inválido"),
	ERRO_DO_SISTEMA("Erro do sistema", "erro-sistema");

	private String title;
	private String uri;

	ProblemType(String title, String uri)
	{
		this.title = title;
		this.uri = "https://algafood.com.br/"+ uri;
	}
}
