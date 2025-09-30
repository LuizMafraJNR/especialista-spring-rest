package com.algaworks.algafoodapi.api.assembler.restaurante;

import com.algaworks.algafoodapi.api.model.input.RestaurantInput;
import com.algaworks.algafoodapi.domain.model.Cidade;
import com.algaworks.algafoodapi.domain.model.Cozinha;
import com.algaworks.algafoodapi.domain.model.Restaurante;
import java.util.Objects;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestauranteDTODisassembler
{
	@Autowired
	private ModelMapper modelMapper;

	public Restaurante toRestaurante(RestaurantInput restaurantInput)
	{
		return modelMapper.map(restaurantInput, Restaurante.class);
	}


	public void copyToDomainObject(RestaurantInput restaurantInput, Restaurante restaurante) {
		// Para evitar identifier of an instance of com.algaworks.algafoodapi.domain.model.Cozinha was altered from 2 to 1
		restaurante.setCozinha(new Cozinha());

		// Para evitar identifier of an instance of com.algaworks.algafoodapi.domain.model.Cozinha was altered from 2 to 1
		if (Objects.nonNull(restaurante.getEndereco())) {
			restaurante.getEndereco().setCidade(new Cidade());
		}
		modelMapper.map(restaurantInput, restaurante);
	}
}
