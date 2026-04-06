package com.algaworks.algafoodapi.api.model;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoOutput
{
	private Long produtoId;
	private String produtoNome;
	private BigDecimal precoUnitario;
	private BigDecimal precoTotal;
	private Integer quantidade;
	private String observacao;
}
