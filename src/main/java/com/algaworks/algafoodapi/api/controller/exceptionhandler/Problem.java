package com.algaworks.algafoodapi.api.controller.exceptionhandler;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Problem
{
	private LocalDateTime dateTime;
	private String message;
}
