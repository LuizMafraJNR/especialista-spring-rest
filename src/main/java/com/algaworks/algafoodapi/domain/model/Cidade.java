package com.algaworks.algafoodapi.domain.model;

import com.algaworks.algafoodapi.core.validation.Groups;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import lombok.Data;

@Data
@Entity
public class Cidade
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	@NotBlank
	private String nome;

	@ManyToOne
	@ConvertGroup(from = Default.class, to = Groups.EstadoId.class)
	@JoinColumn(name = "estado_id", nullable = false)
	@NotNull
	@Valid
	private Estado estado;
}
