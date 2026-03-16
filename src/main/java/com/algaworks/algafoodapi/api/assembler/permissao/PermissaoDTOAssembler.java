package com.algaworks.algafoodapi.api.assembler.permissao;

import com.algaworks.algafoodapi.api.model.PermissaoOutput;
import com.algaworks.algafoodapi.domain.model.Permissao;
import java.util.Collection;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PermissaoDTOAssembler
{
	@Autowired
	private ModelMapper modelMapper;

	public PermissaoOutput toFormaPagamentoDTO(Permissao permissao)
	{
		return modelMapper.map(permissao, PermissaoOutput.class);
	}

	public List<PermissaoOutput> toCollectDto(Collection<Permissao> permissaos)
	{
		return permissaos.stream()
			.map(this::toFormaPagamentoDTO)
			.collect(java.util.stream.Collectors.toList());
	}
}
