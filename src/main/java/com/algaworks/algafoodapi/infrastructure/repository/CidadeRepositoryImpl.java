package com.algaworks.algafoodapi.infrastructure.repository;

import com.algaworks.algafoodapi.domain.model.Cidade;
import com.algaworks.algafoodapi.domain.repository.CidadeRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CidadeRepositoryImpl implements CidadeRepository
{

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Cidade> listar()
	{
		TypedQuery<Cidade> listCidade = entityManager.createQuery("from Cidade", Cidade.class);
		return listCidade.getResultList();
	}

	@Override
	public Cidade buscarPorId(Long id)
	{
		return entityManager.find(Cidade.class, id);
	}

	@Override
	@Transactional
	public Cidade salvar(Cidade cidade)
	{
		return entityManager.merge(cidade);
	}

	@Transactional
	@Override
	public void remover(Cidade cidade)
	{
		cidade = buscarPorId(cidade.getId());
		entityManager.remove(cidade);
	}
}
