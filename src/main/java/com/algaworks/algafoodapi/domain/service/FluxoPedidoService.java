package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.model.Pedido;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FluxoPedidoService
{
	private final PedidoService pedidoService;

	@Transactional
	public void confirmar(Long pedidoId) {
		Pedido pedido = pedidoService.buscarOuFalhar(pedidoId);
		pedido.confirmar();
	}

	@Transactional
	public void cancelar(Long pedidoId) {
		Pedido pedido = pedidoService.buscarOuFalhar(pedidoId);
		pedido.cancelar();
	}

	@Transactional
	public void entregar(Long pedidoId) {
		Pedido pedido = pedidoService.buscarOuFalhar(pedidoId);
		pedido.entregar();
	}
}
