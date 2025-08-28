package com.algaworks.algafoodapi.api.assembler.cidade;

import com.algaworks.algafoodapi.api.model.input.CidadeInput;
import com.algaworks.algafoodapi.domain.model.Cidade;
import com.algaworks.algafoodapi.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CidadeDTODisassembler
{
	@Autowired
	private ModelMapper modelMapper;

	public Cidade toCidade(CidadeInput cidadeInput) {
		return modelMapper.map(cidadeInput, Cidade.class);
	}

	public void copyToDomainObject(CidadeInput cidadeInput, Cidade cidade) {
		// Para evitar identifier of an instance of com.algaworks.algafoodapi.domain.model.Estado was altered from 2 to 1
		cidade.setEstado(new Estado());
		modelMapper.map(cidadeInput, cidade);
	}
}
