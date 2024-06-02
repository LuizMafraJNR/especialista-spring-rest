package com.algaworks.algafoodapi.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)//, reason = "Entidade não encontrada")
public abstract class EntidadeNaoEncontradaException extends NegocioException
{
    private static final long serialVersionUID = 1L;

    public EntidadeNaoEncontradaException(String mensagem)
    {
        super(mensagem);

    }
}

/*
public class EntidadeNaoEncontradaException extends ResponseStatusException
{
    private static final long serialVersionUID = 1L;

    public EntidadeNaoEncontradaException(HttpStatus status, String reason)
    {
        super(status, reason);
    }

    // Padrão
    public EntidadeNaoEncontradaException(String mensagem){
        this(HttpStatus.NOT_FOUND, mensagem);
    }
}
*/
