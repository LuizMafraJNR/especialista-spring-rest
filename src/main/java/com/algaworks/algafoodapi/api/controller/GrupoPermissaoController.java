package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.api.assembler.permissao.PermissaoDTOAssembler;
import com.algaworks.algafoodapi.api.assembler.permissao.PermissaoDTODisassembler;
import com.algaworks.algafoodapi.api.model.PermissaoOutput;
import com.algaworks.algafoodapi.domain.model.Grupo;
import com.algaworks.algafoodapi.domain.service.GrupoService;
import com.algaworks.algafoodapi.domain.service.PermissaoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/grupos/{grupoId}/permissoes")
public class GrupoPermissaoController
{

	@Autowired
	private PermissaoDTOAssembler permissaoDTOAssembler;
	@Autowired
	private GrupoService grupoService;

	@GetMapping()
	public List<PermissaoOutput> listar(@PathVariable Long grupoId) {
		Grupo grupo = grupoService.buscarOuFalhar(grupoId);
		return permissaoDTOAssembler.toCollectDto(grupo.getPermissoes());
	}

	@PutMapping("/{permissaoId}")
	public void associar(@PathVariable Long grupoId,
		@PathVariable Long permissaoId) {
		grupoService.associarPermissao(grupoId, permissaoId);
	}

	@DeleteMapping("/{permissaoId}")
	@ResponseStatus(HttpStatus.OK)
	public void desassociar(@PathVariable Long grupoId,
		@PathVariable Long permissaoId) {
		grupoService.desassociarPermissao(grupoId, permissaoId);
	}
}
