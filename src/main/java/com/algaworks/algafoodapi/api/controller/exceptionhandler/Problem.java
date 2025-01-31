package com.algaworks.algafoodapi.api.controller.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class Problem
{
	private Integer status;
	private String type;
	private String title;
	private String detail;
	private String userMessage;
	private LocalDateTime dateTime;
	private List<Field> fields;

	@Getter
	@Builder
	public static class Field {
		private String name;
		private String userMessage;
	}
}
