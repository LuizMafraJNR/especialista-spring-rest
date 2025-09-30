package com.algaworks.algafoodapi.domain.exception;

public class GrupoNaoEncontradoException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public GrupoNaoEncontradoException(Long grupoId)
  {
    this(String.format("Não existe um cadastro de grupo com código %d", grupoId));
  }

  public GrupoNaoEncontradoException(String message) {
    super(message);
  }
}
