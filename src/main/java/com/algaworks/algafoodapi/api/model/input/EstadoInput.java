package com.algaworks.algafoodapi.api.model.input;

import com.algaworks.algafoodapi.core.validation.Groups;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoInput
{
	@NotNull(groups = Groups.EstadoId.class)
	private Long id;

	@NotBlank
	private String nome;
}
