package com.algaworks.algafoodapi.jpa.cidade;

import com.algaworks.algafoodapi.AlgafoodApiApplication;
import com.algaworks.algafoodapi.domain.model.Cidade;
import com.algaworks.algafoodapi.infrastructure.repository.CidadeRepositoryImpl;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class DeletarCozinhaPorIdMain

{
	public static void main(String[] args)
	{
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
			.web(WebApplicationType.NONE)
			.run(args);

		CidadeRepositoryImpl cidadeRepository = applicationContext.getBean(CidadeRepositoryImpl.class);
		Cidade cidade = new Cidade();
		cidade = cidadeRepository.buscarPorId(1L);
		System.out.printf("Cidade %s foi removida\n", cidade.getNome());
		cidadeRepository.remover(cidade.getId());
	}
}
