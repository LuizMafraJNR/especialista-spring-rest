package com.algaworks.algafoodapi.api.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoInput
{
	@NotBlank
	private String cep;
	@NotBlank
	private String logradouro;
	private String numero;
	@NotBlank
	private String complemento;
	@NotBlank
	private String bairro;
	@Valid
	@NotNull
	private CidadeIdInput cidade;
}
