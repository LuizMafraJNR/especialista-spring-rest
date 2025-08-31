package com.algaworks.algafoodapi.domain.exception;

public class FormaPagamentoNaoEncontradaException extends RuntimeException
{
	public FormaPagamentoNaoEncontradaException(String mensagem)
	{
		super(mensagem);

	}

	public FormaPagamentoNaoEncontradaException(Long estadoId)
	{
		this(String.format("Não existe um cadastro de uma forma de pagamento com código %d", estadoId));
	}
}
