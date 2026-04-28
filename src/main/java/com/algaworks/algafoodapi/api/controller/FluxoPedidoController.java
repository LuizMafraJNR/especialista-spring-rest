package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.api.assembler.pedido.PedidoDTOAssembler;
import com.algaworks.algafoodapi.api.assembler.pedido.PedidoDTODisassembler;
import com.algaworks.algafoodapi.api.assembler.pedido.PedidoResumoDTOAssembler;
import com.algaworks.algafoodapi.api.model.PedidoOutput;
import com.algaworks.algafoodapi.api.model.PedidoResumoOutput;
import com.algaworks.algafoodapi.api.model.input.PedidoInput;
import com.algaworks.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.exception.NegocioException;
import com.algaworks.algafoodapi.domain.model.Pedido;
import com.algaworks.algafoodapi.domain.model.Usuario;
import com.algaworks.algafoodapi.domain.service.FluxoPedidoService;
import com.algaworks.algafoodapi.domain.service.PedidoService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos/{pedidoId}")
@RequiredArgsConstructor
public class FluxoPedidoController
{
	private final FluxoPedidoService fluxoPedidoService;

	@PutMapping("/confirmacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void confirmar(@PathVariable Long pedidoId) {
		fluxoPedidoService.confirmar(pedidoId);
	}

	@PutMapping("/entregar")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void entregar(@PathVariable Long pedidoId) {
		fluxoPedidoService.entregar(pedidoId);
	}

	@PutMapping("/cancelar")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void cancelar(@PathVariable Long pedidoId) {
		fluxoPedidoService.cancelar(pedidoId);
	}
}
