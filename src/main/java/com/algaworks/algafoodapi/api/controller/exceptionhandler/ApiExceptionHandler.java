package com.algaworks.algafoodapi.api.controller.exceptionhandler;

import com.algaworks.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.exception.NegocioException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.IgnoredPropertyException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

// RFC 7807 PROBLEM DETAILS HTTP APIs

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler
{

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
		HttpStatus status, WebRequest request)
	{
		if (ex instanceof MethodArgumentTypeMismatchException)
		{
			return handleMethodArgumentTypeMismatch((MethodArgumentTypeMismatchException) ex, headers, status, request);
		}

		return super.handleTypeMismatch(ex, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
		HttpHeaders headers, HttpStatus status, WebRequest request)
	{
		Throwable rootCause = ExceptionUtils.getRootCause(ex);

		if(rootCause instanceof InvalidFormatException)
		{
			return handleInvalidFormat((InvalidFormatException) rootCause, headers, status, request);
		}
		else if (rootCause instanceof IgnoredPropertyException)
		{
			return  handlePropertyIgnored((IgnoredPropertyException) rootCause, headers, status, request);
		}
		else if (rootCause instanceof UnrecognizedPropertyException)
		{
			return handleJsonMapping((JsonMappingException) rootCause, status, request);
		}

		Problem problem = createProblemBuilder(status, ProblemType.MENSAGEM_INCOMPREENSIVEL,
			"O corpo da requisição está inválido. Verifique erro de sintaxe.").build();
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}

	private ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request)
	{
		ProblemType problemType = ProblemType.PARAMETRO_INVALIDO;
		String detail = String.format("O parâmetro de URL '%s' recebeu o valor '%s', que é de um tipo inválido. "
			+ "Corrija e informe um valor compatível com o tipo %s", ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());
		Problem problem = createProblemBuilder(status, problemType, detail).build();

		return handleExceptionInternal(ex, problem, headers, status, request);
	}

	private ResponseEntity<Object> handleJsonMapping(JsonMappingException rootCause, HttpStatus status, WebRequest request)
	{
		String path = joinPath(rootCause.getPath());
		ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
		String detail = String.format("A propriedade '%s' não existe. Corrija ou remova-a para continuar", path);
		Problem problem = createProblemBuilder(status, problemType, detail).build();

		return handleExceptionInternal(rootCause, problem, new HttpHeaders(), status, request);
	}

	private ResponseEntity<Object> handlePropertyIgnored(IgnoredPropertyException rootCause, HttpHeaders headers, HttpStatus status, WebRequest request)
	{
		String path = joinPath(rootCause.getPath());
		ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
		String detail = String.format("A propriedade '%s' não é suportada. Remova-a para continuar", path);
		Problem problem = createProblemBuilder(status, problemType, detail).build();

		return handleExceptionInternal(rootCause, problem, headers, status, request);
	}

	private String joinPath(List<JsonMappingException.Reference> path)
	{
		return path.stream()
			.map(ref -> ref.getFieldName())
			.collect(Collectors.joining("."));
	}

	private ResponseEntity<Object> handleInvalidFormat(InvalidFormatException rootCause, HttpHeaders headers, HttpStatus status, WebRequest request)
	{
		String path = joinPath(rootCause.getPath());
		ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
		String detail = String.format("A propriedade '%s' recebeu o valor '%s' que é de um tipo inválido."
			+ " Corrija e informe um valor compatível com o tipo %s",path,rootCause.getValue(), rootCause.getTargetType().getSimpleName());

		Problem problem = createProblemBuilder(status, problemType, detail).build();

		return  handleExceptionInternal(rootCause, problem, headers, status, request);
	}

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> handleEstadoNaoEncontradoException(EntidadeNaoEncontradaException e, WebRequest request)
	{
		Problem problem = createProblemBuilder(HttpStatus.NOT_FOUND, ProblemType.ENTIDADE_NAO_ENCONTRADA,
			e.getMessage()).build();
		return handleExceptionInternal(e, problem, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<?> handleEntidadeEmUsoException(WebRequest request, EntidadeEmUsoException e)
	{
		Problem problem = createProblemBuilder(HttpStatus.CONFLICT, ProblemType.ENTIDADE_EM_USO, e.getMessage())
			.build();
		return handleExceptionInternal(e, problem, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}

	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> handleNegocioException(NegocioException e, WebRequest request)
	{
		Problem problem = createProblemBuilder(HttpStatus.BAD_REQUEST, ProblemType.ERRO_NEGOCIO, e.getMessage())
			.build();
	    return handleExceptionInternal(e, problem, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
		HttpHeaders headers, HttpStatus status, WebRequest request)
	{
		if(Objects.isNull(body))
		{
			body = Problem.builder()
				.status(status.value())
				.title(status.getReasonPhrase())
				.dateTime(LocalDateTime.now())
				.build();
		} else if (body instanceof String)
		{
			body = Problem.builder()
				.status(status.value())
				.title((String) body)
				.dateTime(LocalDateTime.now())
				.build();
		}
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}

	private Problem.ProblemBuilder createProblemBuilder(HttpStatus cause, ProblemType problemType, String detail)
	{
		return Problem.builder()
			.status(cause.value())
			.type(problemType.getUri())
			.title(problemType.getTitle())
			.detail(detail)
			.dateTime(LocalDateTime.now());
	}
}
