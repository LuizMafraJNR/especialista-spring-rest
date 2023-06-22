package com.algaworks.algafoodapi.jpa.permissao;

import com.algaworks.algafoodapi.AlgafoodApiApplication;
import com.algaworks.algafoodapi.domain.model.Permissao;
import com.algaworks.algafoodapi.infrastructure.repository.PermissaoRepositoryImpl;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class DeletaPermissaoMain
{
	public static void main(String[] args)
	{
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
			.web(WebApplicationType.NONE)
			.run(args);

		PermissaoRepositoryImpl permissaoRepository = applicationContext.getBean(PermissaoRepositoryImpl.class);

		Permissao permissao = new Permissao();
		permissao = permissaoRepository.buscarPorid(1L);
		System.out.printf("Permissao %s vai ser removida\n", permissao.getNome());
		permissaoRepository.remover(permissao);
	}
}
