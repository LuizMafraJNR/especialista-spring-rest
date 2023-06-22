package com.algaworks.algafoodapi.domain.model;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tab_formPagamento")
public class FormaPagamento
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String descricao;
}
