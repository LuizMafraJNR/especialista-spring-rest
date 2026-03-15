package com.algaworks.algafoodapi.domain.exception;

public class ProdutoNaoEncontradaException extends RuntimeException
{
	public ProdutoNaoEncontradaException(String mensagem)
	{
		super(mensagem);

	}

	public ProdutoNaoEncontradaException(Long produtoId)
	{
		this(String.format("Não existe um cadastro do produto com código %d", produtoId));
	}
}
