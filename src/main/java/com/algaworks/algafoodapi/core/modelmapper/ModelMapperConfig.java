package com.algaworks.algafoodapi.core.modelmapper;

import com.algaworks.algafoodapi.api.model.EnderecoOutput;
import com.algaworks.algafoodapi.api.model.input.ItemPedidoInput;
import com.algaworks.algafoodapi.domain.model.Endereco;
import com.algaworks.algafoodapi.domain.model.ItemPedido;
import com.algaworks.algafoodapi.domain.model.Produto;
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

		modelMapper.createTypeMap(ItemPedidoInput.class, ItemPedido.class)
			.addMappings(mapper -> mapper.skip(ItemPedido::setId));

		var pedidoInputToPedidoTypeMap = modelMapper.createTypeMap(
			com.algaworks.algafoodapi.api.model.input.PedidoInput.class,
			com.algaworks.algafoodapi.domain.model.Pedido.class);

		pedidoInputToPedidoTypeMap.addMappings(mapper -> mapper.skip(
			com.algaworks.algafoodapi.domain.model.Pedido::setId));

		enderecoToEnderecoModelMapper.<String>addMapping(
			enderecoSrc -> enderecoSrc.getCidade().getEstado().getNome(),
			(enderecoModelDest, value) -> enderecoModelDest.getCidade().setEstado(value));
		return modelMapper;
	}

}
