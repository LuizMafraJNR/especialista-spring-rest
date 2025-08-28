package com.algaworks.algafoodapi.api.assembler.cidade;

import com.algaworks.algafoodapi.api.model.CidadeOutput;
import com.algaworks.algafoodapi.domain.model.Cidade;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CidadeDTOAssembler
{
	@Autowired
	private ModelMapper modelMapper;

	public CidadeOutput toCidadeOutput(Cidade cidade) {
		return modelMapper.map(cidade, CidadeOutput.class);
	}

	public List<CidadeOutput> toCollectCidadeOutput(List<Cidade> cidades) {
		return cidades.stream()
			.map(this::toCidadeOutput)
			.collect(Collectors.toList());
	}
}
