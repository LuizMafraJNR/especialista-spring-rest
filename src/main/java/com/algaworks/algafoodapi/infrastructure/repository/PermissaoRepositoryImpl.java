package com.algaworks.algafoodapi.infrastructure.repository;

import com.algaworks.algafoodapi.domain.model.Permissao;
import com.algaworks.algafoodapi.domain.repository.PermissaoRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PermissaoRepositoryImpl implements PermissaoRepository
{

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Permissao> listar()
	{
		TypedQuery<Permissao> listPerm = entityManager.createQuery("from Permissao", Permissao.class);
		return listPerm.getResultList();
	}

	@Override
	public Permissao buscarPorid(Long id)
	{
		return entityManager.find(Permissao.class, id);
	}

	@Transactional
	@Override
	public Permissao salvar(Permissao permissao)
	{
		return entityManager.merge(permissao);
	}

	@Transactional
	@Override
	public void remover(Permissao permissao)
	{
		permissao = buscarPorid(permissao.getId());
		entityManager.remove(permissao);
	}
}
