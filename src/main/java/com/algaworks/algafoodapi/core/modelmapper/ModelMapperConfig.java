package com.algaworks.algafoodapi.core.modelmapper;

import com.algaworks.algafoodapi.api.model.EnderecoOutput;
import com.algaworks.algafoodapi.domain.model.Endereco;
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
		/*modelMapper.createTypeMap(Restaurante.class, RestauranteOutput.class)
			.addMapping(Restaurante::getTaxaFrete, RestauranteOutput::setPrecoFrete);*/
		var enderecoToEnderecoModelMapper = modelMapper.createTypeMap(Endereco.class,
			EnderecoOutput.class);
		enderecoToEnderecoModelMapper.<String>addMapping(
			enderecoSrc -> enderecoSrc.getCidade().getEstado().getNome(),
			(enderecoModelDest, value) -> enderecoModelDest.getCidade().setEstado(value));
		return modelMapper;
	}

}
