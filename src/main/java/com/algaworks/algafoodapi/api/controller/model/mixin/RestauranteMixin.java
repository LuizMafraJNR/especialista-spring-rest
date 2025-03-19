package com.algaworks.algafoodapi.api.controller.model.mixin;

import com.algaworks.algafoodapi.domain.model.Cozinha;
import com.algaworks.algafoodapi.domain.model.Endereco;
import com.algaworks.algafoodapi.domain.model.FormaPagamento;
import com.algaworks.algafoodapi.domain.model.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;
import java.util.List;

public abstract class RestauranteMixin
{
	@JsonIgnoreProperties(value = "nome", allowGetters = true)
	private Cozinha cozinha;
	@JsonIgnore
	private List<FormaPagamento> formasPagamento;
	@JsonIgnore
	private Endereco endereco;
	@JsonIgnore
	private LocalDateTime dataCadastro;
	@JsonIgnore
	private LocalDateTime dataAtualizacao;
	@JsonIgnore
	private List<Produto> produtos;
}
