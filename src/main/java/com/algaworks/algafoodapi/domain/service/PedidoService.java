package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.exception.NegocioException;
import com.algaworks.algafoodapi.domain.exception.PedidoNaoEncontradoException;
import com.algaworks.algafoodapi.domain.model.*;
import com.algaworks.algafoodapi.domain.repository.PedidoRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PedidoService
{
	private final PedidoRepository pedidoRepository;
	private final CadastroRestauranteService cadastroRestauranteService;
	private final CadastroCidadeService cadastroCidadeService;
	private final UsuarioService usuarioService;
	private final CadastroProdutoService cadastroProdutoService;
	private final CadastroFormaPagamentoService cadastroFormaPagamentoService;



	public Pedido salvar(Pedido pedido) {
		validarPedido(pedido);
		validarItens(pedido);

		pedido.setTaxaFrete(pedido.getRestaurante().getTaxaFrete());
		pedido.calcularValorTotal();

		return pedidoRepository.save(pedido);
	}

	public List<Pedido> listar() {
		return pedidoRepository.findAll();
	}

	public Pedido buscarOuFalhar(Long pedidoId) {
		return pedidoRepository.findById(pedidoId)
			.orElseThrow(() -> new PedidoNaoEncontradoException(pedidoId));
	}


	private void validarPedido(Pedido pedido) {
		Cidade cidade = cadastroCidadeService.buscarOuFalhar(pedido.getEnderecoEntrega().getCidade().getId());
		Usuario cliente = usuarioService.buscarOuFalhar(pedido.getCliente().getId());
		Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(pedido.getRestaurante().getId());
		FormaPagamento formaPagamento = cadastroFormaPagamentoService.buscarOuFalhar(pedido.getFormaPagamento().getId());

		pedido.getEnderecoEntrega().setCidade(cidade);
		pedido.setCliente(cliente);
		pedido.setRestaurante(restaurante);		pedido.setFormaPagamento(formaPagamento);

		if (restaurante.naoAceitaFormaPagamento(formaPagamento)) {
			throw new NegocioException(String.format("Forma de pagamento '%s' não é aceita por esse restaurante.",
				formaPagamento.getDescricao()));
		}
	}

	private void validarItens(Pedido pedido) {
		pedido.getItens().forEach(item -> {
			Produto produto = cadastroProdutoService.buscarOuFalhar(
				pedido.getRestaurante().getId(), item.getProduto().getId());

			item.setPedido(pedido);
			item.setProduto(produto);
			item.setPrecoUnitario(produto.getPreco());
		});
	}
}
