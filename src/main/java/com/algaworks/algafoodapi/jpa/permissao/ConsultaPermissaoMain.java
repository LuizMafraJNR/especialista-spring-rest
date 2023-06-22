package com.algaworks.algafoodapi.jpa.permissao;

import com.algaworks.algafoodapi.AlgafoodApiApplication;
import com.algaworks.algafoodapi.domain.model.Permissao;
import com.algaworks.algafoodapi.infrastructure.repository.PermissaoRepositoryImpl;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class ConsultaPermissaoMain
{
	public static void main(String[] args)
	{
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
			.web(WebApplicationType.NONE)
			.run(args);

		PermissaoRepositoryImpl permissaoRepository = applicationContext.getBean(PermissaoRepositoryImpl.class);

		for (Permissao permissao : permissaoRepository.listar()){
			System.out.printf("Permissao nome: %s\nDescricao: %s\n\n", permissao.getNome(), permissao.getDescricao());
		}
	}
}
