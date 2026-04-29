package com.algaworks.algafoodapi.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoOutput
{
//	private Long id;
	private String codigo;
	private BigDecimal subtotal;
	private BigDecimal taxaFrete;
	private BigDecimal valorTotal;


	private EnderecoOutput enderecoEntrega;

	private String status;

	private OffsetDateTime dataCriacao;
	private OffsetDateTime dataConfirmacao;
	private OffsetDateTime dataCancelamento;
	private OffsetDateTime dataEntrega;

	private FormaPagamentoOutput formaPagamento;

	private RestauranteResumoOutput restaurante;

	private UsuarioOutput cliente;

	private List<ItemPedidoOutput> itens = new ArrayList<>();
}
