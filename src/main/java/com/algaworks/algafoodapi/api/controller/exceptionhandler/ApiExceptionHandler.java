package com.algaworks.algafoodapi.api.controller.exceptionhandler;

import com.algaworks.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.exception.NegocioException;
import java.time.LocalDateTime;
import java.util.Objects;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler
{
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> tratarEstadoNaoEncontradoException(EntidadeNaoEncontradaException e, WebRequest request)
	{
		return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<?> tratarEntidadeEmUsoException(WebRequest request, EntidadeEmUsoException e)
	{
		return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
	}

	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> tratarNegocioException(NegocioException e, WebRequest request)
	{
	    return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
		HttpHeaders headers, HttpStatus status, WebRequest request)
	{
		if(Objects.isNull(body))
		{
			body = Problem.builder()
				.dateTime(LocalDateTime.now())
				.message(status.getReasonPhrase())
				.build();
		} else if (body instanceof String)
		{
			body = Problem.builder()
				.dateTime(LocalDateTime.now())
				.message((String) body)
				.build();
		}
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
}
