package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.api.assembler.usuario.UsuarioDTOAssembler;
import com.algaworks.algafoodapi.api.assembler.usuario.UsuarioDTODisassembler;
import com.algaworks.algafoodapi.api.model.UsuarioOutput;
import com.algaworks.algafoodapi.api.model.input.UsuarioComSenhaInput;
import com.algaworks.algafoodapi.api.model.input.UsuarioInput;
import com.algaworks.algafoodapi.api.model.input.UsuarioSenhaInput;
import com.algaworks.algafoodapi.domain.model.Usuario;
import com.algaworks.algafoodapi.domain.repository.UsuarioRepository;
import com.algaworks.algafoodapi.domain.service.UsuarioService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController
{
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private UsuarioDTOAssembler usuarioDTOAssembler;
	@Autowired
	private UsuarioDTODisassembler usuarioDTODisassembler;

	@GetMapping
	public List<UsuarioOutput> listar()
	{
		return usuarioDTOAssembler.toCollectionDTO(usuarioRepository.findAll());
	}

	@GetMapping("/{usuarioId}")
	public UsuarioOutput buscar(@PathVariable Long usuarioId)
	{
		return usuarioDTOAssembler.toDTO(usuarioService.buscarOuFalhar(usuarioId));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioOutput adicionr(@RequestBody @Valid UsuarioComSenhaInput usuarioComSenhaInput)
	{
		Usuario usuario = usuarioDTODisassembler.toDomainObject(usuarioComSenhaInput);
		usuario = usuarioService.salvar(usuario);

		return usuarioDTOAssembler.toDTO(usuario);
	}

	@PutMapping("/{usuarioId}")
	public UsuarioOutput atualizar(@PathVariable Long usuarioId,
		@RequestBody @Valid UsuarioInput usuarioInput)
	{
		Usuario usuarioAtual = usuarioService.buscarOuFalhar(usuarioId);
		usuarioDTODisassembler.copyToDomainObject(usuarioInput, usuarioAtual);
		usuarioAtual = usuarioService.salvar(usuarioAtual);

		return usuarioDTOAssembler.toDTO(usuarioAtual);
	}

	@PutMapping("/{usuarioId}/senha")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void alterarSenha(@PathVariable Long usuarioId,
		@RequestBody @Valid UsuarioSenhaInput usuarioSenhaInput)
	{
		usuarioService.alterarSenha(usuarioId, usuarioSenhaInput.getSenhaAtual(),
			usuarioSenhaInput.getNovaSenha());
	}
}
