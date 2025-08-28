package com.algaworks.algafoodapi.api.assembler.estado;

import com.algaworks.algafoodapi.api.model.EstadoOutput;
import com.algaworks.algafoodapi.domain.model.Estado;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EstadoDTOAssembler
{
	@Autowired
	private ModelMapper modelMapper;

	public EstadoOutput toEstadoOutput(Estado estado) {
		return modelMapper.map(estado, EstadoOutput.class);
	}

	public List<EstadoOutput> toCollectEstadoOutput(List<Estado> estados) {
		return estados.stream()
			.map(this::toEstadoOutput)
			.collect(Collectors.toList());
	}
}
