package com.algaworks.algafoodapi.jpa.restaurante;

import com.algaworks.algafoodapi.AlgafoodApiApplication;
import com.algaworks.algafoodapi.domain.model.Restaurante;
import com.algaworks.algafoodapi.infrastructure.repository.RestauranteRepositoryImpl;
import java.math.BigDecimal;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class InsereRestauranteMain
{
	public static void main(String[] args)
	{
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
			.web(WebApplicationType.NONE)
			.run(args);

		RestauranteRepositoryImpl restauranteRepository = applicationContext.getBean(
			RestauranteRepositoryImpl.class);

		CozinhaRepositoryImpl cozinhaRepository = applicationContext.getBean(CozinhaRepositoryImpl.class);


		Restaurante restaurante = new Restaurante();
		restaurante.setNome("Neominder");
		restaurante.setTaxaFrete(new BigDecimal("3.5"));
		restaurante.setCozinha(cozinhaRepository.buscar(3L));
		restauranteRepository.cadastrar(restaurante);
		System.out.println("Restaurante cadastrado: "+restaurante.getNome()+" com a taxa de "+restaurante.getTaxaFrete());
	}
}
