package com.algaworks.algafoodapi.domain.model;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
public class Cidade
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String nome;
	@ManyToOne
	@JoinColumn(name = "estado_id", nullable = false)
	private Estado estado;
}
