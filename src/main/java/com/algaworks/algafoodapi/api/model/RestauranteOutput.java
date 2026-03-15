package com.algaworks.algafoodapi.api.model;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteOutput
{
	private Long id;
	private String nome;
	private BigDecimal taxaFrete;
	private CozinhaOutput cozinha;
	private EnderecoModel endereco;
	private Boolean ativo;
	private Boolean aberto;
}
