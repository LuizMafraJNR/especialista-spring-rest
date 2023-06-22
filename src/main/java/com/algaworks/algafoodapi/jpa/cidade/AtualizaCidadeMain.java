package com.algaworks.algafoodapi.jpa.cidade;

import com.algaworks.algafoodapi.AlgafoodApiApplication;
import com.algaworks.algafoodapi.domain.model.Cidade;
import com.algaworks.algafoodapi.infrastructure.repository.CidadeRepositoryImpl;
import com.algaworks.algafoodapi.infrastructure.repository.EstadoRepositoryImpl;
import java.util.Arrays;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class AtualizaCidadeMain
{
	public static void main(String[] args)
	{
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
			.web(WebApplicationType.NONE)
			.run(args);

		CidadeRepositoryImpl cidadeRepository = applicationContext.getBean(CidadeRepositoryImpl.class);
		EstadoRepositoryImpl estadoRepository = applicationContext.getBean(EstadoRepositoryImpl.class);

		Cidade cidade = new Cidade();
		cidade.setId(2L);
		cidade.setNome("Rio Negrinho");
		cidade.setEstado(estadoRepository.buscarPorId(2L));
		cidadeRepository.salvar(cidade);
		System.out.printf("Cidade %s - Estado %s\n", cidade.getNome(), cidade.getEstado().getNome());
	}
}
