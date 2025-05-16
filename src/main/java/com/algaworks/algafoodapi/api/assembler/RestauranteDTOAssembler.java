package com.algaworks.algafoodapi.api.assembler;

import com.algaworks.algafoodapi.api.controller.RestauranteController;
import com.algaworks.algafoodapi.api.model.CozinhaDTO;
import com.algaworks.algafoodapi.api.model.RestauranteDTO;
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

	public RestauranteDTO toRestauranteDTO(Restaurante restaurante)
	{
		return modelMapper.map(restaurante, RestauranteDTO.class);
	}

	public List<RestauranteDTO> toCollectDto(List<Restaurante> restaurantes)
	{
		return restaurantes.stream()
			.map(this::toRestauranteDTO)
			.collect(Collectors.toList());
	}
}
