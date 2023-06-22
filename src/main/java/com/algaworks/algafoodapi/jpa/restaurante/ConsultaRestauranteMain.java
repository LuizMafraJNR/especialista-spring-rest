package com.algaworks.algafoodapi.jpa.restaurante;

import com.algaworks.algafoodapi.AlgafoodApiApplication;
import com.algaworks.algafoodapi.domain.model.Restaurante;
import com.algaworks.algafoodapi.infrastructure.repository.RestauranteRepositoryImpl;
import java.util.List;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class ConsultaRestauranteMain
{
	public static void main(String[] args)
	{
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
			.web(WebApplicationType.NONE)
			.run(args);

		RestauranteRepositoryImpl restauranteRepository = applicationContext.getBean(
			RestauranteRepositoryImpl.class);

		List<Restaurante> list = restauranteRepository.listar();
		for (Restaurante objeto : list){
			System.out.println(objeto.getNome()+"\n");
		}
	}
}
