package com.algaworks.algafoodapi.api.assembler;

import com.algaworks.algafoodapi.api.model.input.RestaurantInput;
import com.algaworks.algafoodapi.domain.model.Cozinha;
import com.algaworks.algafoodapi.domain.model.Restaurante;
import org.springframework.stereotype.Component;

@Component
public class RestauranteDTODisassembler
{
	public Restaurante toRestaurante(RestaurantInput restaurantInput)
	{
		Restaurante restaurante = new Restaurante();
		Cozinha cozinha = new Cozinha();

		cozinha.setId(restaurantInput.getCozinha().getId());
		restaurante.setNome(restaurantInput.getNome());
		restaurante.setTaxaFrete(restaurantInput.getTaxaFrete());
		restaurante.setCozinha(cozinha);

		return restaurante;
	}
}
