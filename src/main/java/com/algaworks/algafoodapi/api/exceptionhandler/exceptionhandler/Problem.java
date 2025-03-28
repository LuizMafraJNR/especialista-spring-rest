package com.algaworks.algafoodapi.api.exceptionhandler.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.OffsetDateTime;
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
	private OffsetDateTime dateTime;
	private List<Object> objects;

	@Getter
	@Builder
	public static class Object {
		private String name;
		private String userMessage;
	}
}
