package com.algaworks.algafoodapi.api.assembler.permissao;

import com.algaworks.algafoodapi.api.model.input.PermissaoInput;
import com.algaworks.algafoodapi.domain.model.Permissao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PermissaoDTODisassembler
{
	@Autowired
	private ModelMapper modelMapper;

	public Permissao toFormaPagamento(PermissaoInput permissaoInput)
	{
		return modelMapper.map(permissaoInput, Permissao.class);
	}

	public void copyToDomainObject(PermissaoInput permissaoInput, Permissao permissao) {
		modelMapper.map(permissaoInput, permissaoInput);
	}
}
