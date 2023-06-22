package com.algaworks.algafoodapi.jpa.restaurante;

import com.algaworks.algafoodapi.AlgafoodApiApplication;
import com.algaworks.algafoodapi.domain.model.Restaurante;
import com.algaworks.algafoodapi.infrastructure.repository.RestauranteRepositoryImpl;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class DeletaRestaurantePorIdMain
{
	public static void main(String[] args)
	{
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
			.web(WebApplicationType.NONE)
			.run(args);

		RestauranteRepositoryImpl restauranteRepository = applicationContext.getBean(
			RestauranteRepositoryImpl.class);

		Restaurante restaurante = new Restaurante();
		restaurante = restauranteRepository.buscarId(4L);
		System.out.println("Foi deletado "+restaurante.getNome());
		restauranteRepository.remover(restaurante);

	}
}
