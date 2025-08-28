package com.algaworks.algafoodapi.api.assembler.cozinha;

import com.algaworks.algafoodapi.api.model.CidadeOutput;
import com.algaworks.algafoodapi.api.model.CozinhaOutput;
import com.algaworks.algafoodapi.domain.model.Cozinha;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CozinhaDTOAssembler
{
	@Autowired
	private ModelMapper modelMapper;

	public CozinhaOutput toCozinhaOutput(Cozinha cozinha) {
		return modelMapper.map(cozinha, CozinhaOutput.class);
	}

	public List<CozinhaOutput> toCollectCozinhaOutput(List<Cozinha> cozinhas) {
		return cozinhas.stream()
			.map(this::toCozinhaOutput)
			.collect(Collectors.toList());
	}
}
