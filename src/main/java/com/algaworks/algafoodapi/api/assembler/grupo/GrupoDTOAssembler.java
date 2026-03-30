package com.algaworks.algafoodapi.api.assembler.grupo;

import com.algaworks.algafoodapi.api.model.GrupoOutput;
import com.algaworks.algafoodapi.domain.model.Grupo;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GrupoDTOAssembler
{
	@Autowired
	private ModelMapper modelMapper;

	public GrupoOutput toGrupoDTO(Grupo grupo) {
		return modelMapper.map(grupo, GrupoOutput.class);
	}

	public List<GrupoOutput> toCollectionDTO(Collection<Grupo> grupos) {
		return grupos.stream()
				.map(this::toGrupoDTO)
				.collect(Collectors.toList());
	}
}
