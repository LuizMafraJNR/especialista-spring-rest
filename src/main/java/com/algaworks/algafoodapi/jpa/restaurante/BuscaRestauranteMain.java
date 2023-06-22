package com.algaworks.algafoodapi.jpa.restaurante;

import com.algaworks.algafoodapi.AlgafoodApiApplication;
import com.algaworks.algafoodapi.domain.model.Restaurante;
import com.algaworks.algafoodapi.infrastructure.repository.CozinhaRepositoryImpl;
import com.algaworks.algafoodapi.infrastructure.repository.RestauranteRepositoryImpl;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class BuscaRestauranteMain
{
	public static void main(String[] args)
	{
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
			.web(WebApplicationType.NONE)
			.run(args);

		RestauranteRepositoryImpl restauranteRepository = applicationContext.getBean(
			RestauranteRepositoryImpl.class);

		Restaurante restaurante = restauranteRepository.buscarId(2L);
		System.out.println(restaurante.getNome());

	}
}
