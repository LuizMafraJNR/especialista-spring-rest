package com.algaworks.algafoodapi.api.assembler;

import com.algaworks.algafoodapi.api.model.input.RestaurantInput;
import com.algaworks.algafoodapi.domain.model.Restaurante;
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
}
