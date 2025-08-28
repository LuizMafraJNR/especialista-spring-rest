package com.algaworks.algafoodapi.api.assembler.cozinha;

import com.algaworks.algafoodapi.api.model.input.CozinhaInput;
import com.algaworks.algafoodapi.domain.model.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CozinhaDTODisassembler
{
	@Autowired
	private ModelMapper modelMapper;

	public Cozinha toCozinha(CozinhaInput cozinhaInput) {
		return modelMapper.map(cozinhaInput, Cozinha.class);
	}

	public void copyToDomainObject(CozinhaInput cozinhaInput, Cozinha cozinha) {
		modelMapper.map(cozinhaInput, cozinha);
	}
}
