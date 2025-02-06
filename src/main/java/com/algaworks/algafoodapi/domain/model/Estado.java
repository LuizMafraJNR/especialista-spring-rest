package com.algaworks.algafoodapi.domain.model;

import com.algaworks.algafoodapi.core.validation.Groups;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class Estado
{
	@Id
	@NotNull(groups = Groups.EstadoId.class)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	@NotBlank
	private String nome;
}
