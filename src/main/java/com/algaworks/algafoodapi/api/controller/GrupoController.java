package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.api.assembler.grupo.GrupoDTOAssembler;
import com.algaworks.algafoodapi.api.assembler.grupo.GrupoDTODisassembler;
import com.algaworks.algafoodapi.api.model.GrupoOutput;
import com.algaworks.algafoodapi.api.model.input.GrupoInput;
import com.algaworks.algafoodapi.domain.model.Grupo;
import com.algaworks.algafoodapi.domain.repository.GrupoRepository;
import com.algaworks.algafoodapi.domain.service.GrupoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/grupos")
public class GrupoController
{
	@Autowired
	private GrupoRepository grupoRepository;
	@Autowired
	private GrupoService grupoService;
	@Autowired
	private GrupoDTODisassembler grupoDTODisassembler;
	@Autowired
	private GrupoDTOAssembler grupoDTOAssembler;

	@GetMapping
	public List<GrupoOutput> listar() {
		List<Grupo> grupos = grupoRepository.findAll();
		return grupoDTOAssembler.toCollectionDTO(grupos);
	}

	@GetMapping("/{id}")
	public GrupoOutput buscar(@PathVariable Long id)
	{
		Grupo grupo = grupoService.buscarOuFalhar(id);
		return grupoDTOAssembler.toGrupoDTO(grupo);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public GrupoOutput adicionar(@RequestBody GrupoInput grupoInput)
	{
		Grupo grupo = grupoDTODisassembler.toGrupo(grupoInput);
		grupo = grupoService.salvar(grupo);
		return grupoDTOAssembler.toGrupoDTO(grupo);
	}

	@PutMapping("/{id}")
	public GrupoOutput atualizar(@PathVariable Long id, @RequestBody GrupoInput grupoInput)
	{
		Grupo grupoAtual = grupoService.buscarOuFalhar(id);
		grupoDTODisassembler.copyToDomainObject(grupoInput, grupoAtual);
		grupoAtual = grupoService.salvar(grupoAtual);
		return grupoDTOAssembler.toGrupoDTO(grupoAtual);
	}

	@DeleteMapping("/{id}")
	public void remover(@PathVariable Long id)
	{
		grupoService.excluir(id);
	}


}
