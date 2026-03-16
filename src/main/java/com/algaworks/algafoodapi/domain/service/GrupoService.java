package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafoodapi.domain.exception.GrupoNaoEncontradoException;
import com.algaworks.algafoodapi.domain.model.Grupo;
import com.algaworks.algafoodapi.domain.model.Permissao;
import com.algaworks.algafoodapi.domain.repository.GrupoRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class GrupoService
{
	@Autowired
	private GrupoRepository grupoRepository;
	@Autowired
	private PermissaoService permissaoService;

	@Transactional
	public Grupo salvar(Grupo grupo) {
		return grupoRepository.save(grupo);
	}

	@Transactional
	public void excluir(Long grupoId) {
		try {
			grupoRepository.deleteById(grupoId);
			grupoRepository.flush();
		}
		catch (EmptyResultDataAccessException e) {
			throw new GrupoNaoEncontradoException(grupoId);
		}
		catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
				String.format("Grupo de código %d não pode ser removido, pois está em uso", grupoId)
			);
		}
	}

	public Grupo buscarOuFalhar(Long grupoId) {
		return grupoRepository.findById(grupoId)
			.orElseThrow(() -> new GrupoNaoEncontradoException(grupoId));
	}

	@Transactional
	public void associarPermissao(Long grupoId, Long permissaoId) {
		Grupo busGrupo = buscarOuFalhar(grupoId);
		Permissao permissao = permissaoService.buscarOuFalhar(permissaoId);
		busGrupo.associarPermissao(permissao);
	}

	@Transactional
	public void desassociarPermissao(Long grupoId, Long permissaoId) {
		Grupo busGrupo = buscarOuFalhar(grupoId);
		Permissao permissao = permissaoService.buscarOuFalhar(permissaoId);
		busGrupo.desassociarPermissao(permissao);
	}
}
