package com.algaworks.algafoodapi.jpa.cidade;

import com.algaworks.algafoodapi.AlgafoodApiApplication;
import com.algaworks.algafoodapi.domain.model.Cidade;
import com.algaworks.algafoodapi.infrastructure.repository.CidadeRepositoryImpl;
import com.algaworks.algafoodapi.infrastructure.repository.EstadoRepositoryImpl;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class InsereCidadeMain

{
	public static void main(String[] args)
	{
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
			.web(WebApplicationType.NONE)
			.run(args);

		CidadeRepositoryImpl cidadeRepository = applicationContext.getBean(CidadeRepositoryImpl.class);
		EstadoRepositoryImpl estadoRepository = applicationContext.getBean(EstadoRepositoryImpl.class);

		Cidade cidade = new Cidade();
		cidade.setNome("Jarágua");
		cidade.setEstado(estadoRepository.buscarPorId(1L));
		cidadeRepository.salvar(cidade);
		System.out.printf("Cidade %s foi salva no estado de %s\n", cidade.getNome(), cidade.getEstado().getNome());
	}
}
