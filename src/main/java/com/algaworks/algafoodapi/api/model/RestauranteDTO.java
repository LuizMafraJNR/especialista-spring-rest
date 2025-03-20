package com.algaworks.algafoodapi.api.model;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteDTO
{
	private Long id;
	private String nome;
	private BigDecimal taxaFrete;
	private CozinhaDTO cozinha;

	/*
	*{
	* "id": 1,
	* "nome": "Thai Gourmet",
	* "taxaFrete": 10.00,
	* "cozinha": {
	*   "id": 1,
	*   "nome": "Tailandesa"
	* }
	*
	*
	* */

}
