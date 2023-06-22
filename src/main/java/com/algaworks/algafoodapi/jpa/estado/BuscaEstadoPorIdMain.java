package com.algaworks.algafoodapi.jpa.estado;

import com.algaworks.algafoodapi.AlgafoodApiApplication;
import com.algaworks.algafoodapi.domain.model.Cidade;
import com.algaworks.algafoodapi.domain.model.Estado;
import com.algaworks.algafoodapi.infrastructure.repository.CidadeRepositoryImpl;
import com.algaworks.algafoodapi.infrastructure.repository.EstadoRepositoryImpl;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class BuscaEstadoPorIdMain
{
	public static void main(String[] args)
	{
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
			.web(WebApplicationType.NONE)
			.run(args);

		EstadoRepositoryImpl estadoRepository = applicationContext.getBean(EstadoRepositoryImpl.class);

		Estado estado = new Estado();
		estado = estadoRepository.buscarPorId(3L);
		System.out.printf("Estado %s encontrada\n", estado.getNome());
	}
}
