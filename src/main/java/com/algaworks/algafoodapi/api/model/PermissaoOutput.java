package com.algaworks.algafoodapi.api.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissaoOutput
{
	private Long id;
	private String nome;
	private String descricao;
}
