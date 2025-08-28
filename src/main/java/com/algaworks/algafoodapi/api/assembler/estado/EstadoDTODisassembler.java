package com.algaworks.algafoodapi.api.assembler.estado;

import com.algaworks.algafoodapi.api.model.input.EstadoInput;
import com.algaworks.algafoodapi.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EstadoDTODisassembler
{
	@Autowired
	private ModelMapper modelMapper;

	public Estado toEstado(EstadoInput estadoInput)
	{
		return modelMapper.map(estadoInput, Estado.class);
	}

	public void copyToDomainObject(EstadoInput estadoInput, Estado estado) {
		modelMapper.map(estadoInput, estado);
	}
}
