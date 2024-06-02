package com.algaworks.algafoodapi.api.controller.exceptionhandler;

import com.algaworks.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.exception.NegocioException;
import java.time.LocalDateTime;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler
{
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> tratarEstadoNaoEncontradoException(EntidadeNaoEncontradaException e)
	{
		Problem problema = Problem.builder()
			.dateTime(LocalDateTime.now())
			.message(e.getMessage())
			.build();
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
			.body(problema);
	}

	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<?> tratarEntidadeEmUsoException()
	{
		Problem problema = Problem.builder()
			.dateTime(LocalDateTime.now())
			.message("Entidade está sendo utilizada por outra entidade, por isso não pode ser removida")
			.build();
		return ResponseEntity.status(HttpStatus.CONFLICT)
			.body(problema);
	}

	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> tratarNegocioException(NegocioException e)
	{
		Problem problema = Problem.builder()
			.dateTime(LocalDateTime.now())
			.message(e.getMessage())
			.build();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			.body(problema);
	}

	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseEntity<?> tratarUnsuportedType()
	{
		Problem problema = Problem.builder()
			.dateTime(LocalDateTime.now())
			.message("O tipo de mídia não é suportado")
			.build();
		return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
			.body(problema);
	}
}
