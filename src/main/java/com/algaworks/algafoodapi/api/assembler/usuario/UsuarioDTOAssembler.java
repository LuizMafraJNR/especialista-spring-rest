package com.algaworks.algafoodapi.api.assembler.usuario;

import com.algaworks.algafoodapi.api.model.UsuarioOutput;
import com.algaworks.algafoodapi.domain.model.Usuario;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioDTOAssembler
{
	@Autowired
	private ModelMapper modelMapper;

	public UsuarioOutput toDTO(Usuario usuario)
	{
		return modelMapper.map(usuario, UsuarioOutput.class);
	}

	public List<UsuarioOutput> toCollectionDTO(List<Usuario> usuarios) {
		return usuarios.stream()
			.map(this::toDTO)
			.collect(Collectors.toList());
	}
}
