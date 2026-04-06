package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.exception.PedidoNaoEncontradoException;
import com.algaworks.algafoodapi.domain.model.Pedido;
import com.algaworks.algafoodapi.domain.repository.PedidoRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PedidoService
{
	private final PedidoRepository pedidoRepository;

	public List<Pedido> listar() {
		return pedidoRepository.findAll();
	}

	public Pedido buscarOuFalhar(Long pedidoId) {
		return pedidoRepository.findById(pedidoId)
			.orElseThrow(() -> new PedidoNaoEncontradoException(pedidoId));
	}
}
