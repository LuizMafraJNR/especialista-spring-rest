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
import com.algaworks.algafoodapi.domain.service.PedidoService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController
{
	private final PedidoService pedidoService;
	private final PedidoDTOAssembler pedidoDTOAssembler;
	private final PedidoResumoDTOAssembler pedidoResumoDTOAssembler;
	private final PedidoDTODisassembler pedidoDTODisassembler;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PedidoOutput salvar(@RequestBody @Valid PedidoInput pedidoInput) {
		try
		{
			Pedido pedido = pedidoDTODisassembler.toDomain(pedidoInput);

			// TODO pegar usuário autenticado
			pedido.setCliente(new Usuario());
			pedido.getCliente().setId(1L);

			return pedidoDTOAssembler.toResponse(pedidoService.salvar(pedido));
		}
		catch (EntidadeNaoEncontradaException e)
		{
			throw new NegocioException(e.getMessage());
		}
	}

	@GetMapping()
	public List<PedidoResumoOutput> listar() {
		List<Pedido> todosPedidos = pedidoService.listar();
		return pedidoResumoDTOAssembler.toCollectionResponse(todosPedidos);
	}

	@GetMapping("/{codigoPedido}")
	public PedidoOutput buscarPorId(@PathVariable String codigoPedido) {
		Pedido pedido = pedidoService.buscarOuFalhar(codigoPedido);
		return pedidoDTOAssembler.toResponse(pedido);
	}
}
