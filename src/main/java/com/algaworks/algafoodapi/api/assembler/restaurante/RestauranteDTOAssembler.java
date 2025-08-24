package com.algaworks.algafoodapi.api.assembler.restaurante;

import com.algaworks.algafoodapi.api.model.RestauranteOutput;
import com.algaworks.algafoodapi.domain.model.Restaurante;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestauranteDTOAssembler
{
	@Autowired
	private ModelMapper modelMapper;

	public RestauranteOutput toRestauranteDTO(Restaurante restaurante)
	{
		return modelMapper.map(restaurante, RestauranteOutput.class);
	}

	public List<RestauranteOutput> toCollectDto(List<Restaurante> restaurantes)
	{
		return restaurantes.stream()
			.map(this::toRestauranteDTO)
			.collect(Collectors.toList());
	}
}
