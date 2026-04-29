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
	public void confirmar(String codigoPedido) {
		Pedido pedido = pedidoService.buscarOuFalhar(codigoPedido);
		pedido.confirmar();
	}

	@Transactional
	public void cancelar(String codigoPedido) {
		Pedido pedido = pedidoService.buscarOuFalhar(codigoPedido);
		pedido.cancelar();
	}

	@Transactional
	public void entregar(String codigoPedido) {
		Pedido pedido = pedidoService.buscarOuFalhar(codigoPedido);
		pedido.entregar();
	}
}
