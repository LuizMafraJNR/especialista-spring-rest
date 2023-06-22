package com.algaworks.algafoodapi.jpa.permissao;

import com.algaworks.algafoodapi.AlgafoodApiApplication;
import com.algaworks.algafoodapi.domain.model.Permissao;
import com.algaworks.algafoodapi.infrastructure.repository.PermissaoRepositoryImpl;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class AtualizaPermissaoMain
{
	public static void main(String[] args)
	{
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
			.web(WebApplicationType.NONE)
			.run(args);
		PermissaoRepositoryImpl permissaoRepository = applicationContext.getBean(PermissaoRepositoryImpl.class);
		Permissao permissao = new Permissao();
		permissao.setNome("Cliente");
		permissao.setDescricao("O cliente pode criar conta, logar, comprar e pagar.");
		permissao.setId(1L);
		permissaoRepository.salvar(permissao);
		System.out.printf("Permissao alterada para %s com a descricao: %s\n", permissao.getNome(), permissao.getDescricao());
	}
}
