package com.algaworks.algafoodapi.core.modelmapper;

import com.algaworks.algafoodapi.api.model.RestauranteOutput;
import com.algaworks.algafoodapi.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig
{
	@Bean
	public ModelMapper modelMapper()
	{
		var modelMapper = new ModelMapper();
		modelMapper.createTypeMap(Restaurante.class, RestauranteOutput.class)
			.addMapping(Restaurante::getTaxaFrete, RestauranteOutput::setPrecoFrete);
		return modelMapper;
	}

}
