package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.api.assembler.grupo.GrupoDTOAssembler;
import com.algaworks.algafoodapi.api.model.GrupoOutput;
import com.algaworks.algafoodapi.domain.model.Usuario;
import com.algaworks.algafoodapi.domain.service.UsuarioService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios/{usuarioId}/grupos")
@RequiredArgsConstructor
public class UsuarioGrupoController
{

	private final UsuarioService usuarioService;
	private final GrupoDTOAssembler grupoDTOAssembler;

	@GetMapping()
	public List<GrupoOutput> listar(@PathVariable Long usuarioId) {
		Usuario usuario = usuarioService.buscarOuFalhar(usuarioId);
		return grupoDTOAssembler.toCollectionDTO(usuario.getGrupos());
	}

	@PutMapping("/{grupoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void associarGrupo(@PathVariable Long grupoId,
		@PathVariable Long usuarioId) {
		usuarioService.associarGrupo(usuarioId, grupoId);
	}

	@DeleteMapping("/{grupoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desassociarGrupo(@PathVariable Long grupoId,
		@PathVariable Long usuarioId) {
		usuarioService.desassociarGrupo(usuarioId, grupoId);
	}
}
