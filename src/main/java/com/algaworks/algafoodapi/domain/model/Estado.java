package com.algaworks.algafoodapi.domain.model;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
public class Estado
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String nome;
}
