package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.api.assembler.pedido.PedidoDTOAssembler;
import com.algaworks.algafoodapi.api.assembler.pedido.PedidoResumoDTOAssembler;
import com.algaworks.algafoodapi.api.model.PedidoOutput;
import com.algaworks.algafoodapi.api.model.PedidoResumoOutput;
import com.algaworks.algafoodapi.domain.model.Pedido;
import com.algaworks.algafoodapi.domain.service.PedidoService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController
{
	private final PedidoService pedidoService;
	private final PedidoDTOAssembler pedidoDTOAssembler;
	private final PedidoResumoDTOAssembler pedidoResumoDTOAssembler;

	@GetMapping()
	public List<PedidoResumoOutput> listar() {
		List<Pedido> todosPedidos = pedidoService.listar();
		return pedidoResumoDTOAssembler.toCollectionResponse(todosPedidos);
	}

	@GetMapping("/{pedidoId}")
	public PedidoOutput buscarPorId(@PathVariable Long pedidoId) {
		Pedido pedido = pedidoService.buscarOuFalhar(pedidoId);
		return pedidoDTOAssembler.toResponse(pedido);
	}
}
