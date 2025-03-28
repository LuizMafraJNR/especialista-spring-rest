package com.algaworks.algafoodapi.api.assembler;

import com.algaworks.algafoodapi.api.controller.RestauranteController;
import com.algaworks.algafoodapi.api.model.CozinhaDTO;
import com.algaworks.algafoodapi.api.model.RestauranteDTO;
import com.algaworks.algafoodapi.domain.model.Restaurante;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class RestauranteDTOAssembler
{
	public static RestauranteDTO toRestauranteDTO(Restaurante restaurante)
	{
		RestauranteDTO restauranteDTO = new RestauranteDTO();
		restauranteDTO.setId(restaurante.getId());
		restauranteDTO.setNome(restaurante.getNome());
		restauranteDTO.setTaxaFrete(restaurante.getTaxaFrete());

		CozinhaDTO cozinhaDTO = new CozinhaDTO();
		cozinhaDTO.setId(restaurante.getCozinha().getId());
		cozinhaDTO.setNome(restaurante.getCozinha().getNome());
		restauranteDTO.setCozinha(cozinhaDTO);
		return restauranteDTO;
	}

	public List<RestauranteDTO> toCollectDto(List<Restaurante> restaurantes)
	{
		return restaurantes.stream()
			.map(RestauranteDTOAssembler::toRestauranteDTO)
			.collect(Collectors.toList());
	}
}
