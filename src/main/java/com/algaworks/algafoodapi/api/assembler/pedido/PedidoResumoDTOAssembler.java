package com.algaworks.algafoodapi.api.assembler.pedido;

import com.algaworks.algafoodapi.api.model.PedidoOutput;
import com.algaworks.algafoodapi.api.model.PedidoResumoOutput;
import com.algaworks.algafoodapi.domain.model.Pedido;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PedidoResumoDTOAssembler
{

	private final ModelMapper modelMapper;

	public PedidoResumoOutput toResponse(Pedido pedido) {
		return modelMapper.map(pedido, PedidoResumoOutput.class);
	}

	public List<PedidoResumoOutput> toCollectionResponse(List<Pedido> pedidoList) {
		return pedidoList.stream()
			.map(pedido -> toResponse(pedido))
			.collect(Collectors.toList());
	}
}
