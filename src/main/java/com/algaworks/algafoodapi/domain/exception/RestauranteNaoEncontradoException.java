package com.algaworks.algafoodapi.domain.exception;

public class RestauranteNaoEncontradoException extends EntidadeNaoEncontradaException
{
    private static final long serialVersionUID = 1L;

    public RestauranteNaoEncontradoException(String mensagem)
    {
        super(mensagem);

    }

    public RestauranteNaoEncontradoException(Long estadoId)
    {
        this(String.format("Não existe um cadastro de restaurante com código %d", estadoId));
    }
}