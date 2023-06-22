package com.algaworks.algafoodapi.infrastructure.repository;

import com.algaworks.algafoodapi.domain.model.Estado;
import com.algaworks.algafoodapi.domain.repository.EstadoRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class EstadoRepositoryImpl implements EstadoRepository
{

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Estado> listar()
	{
		TypedQuery<Estado> listEstado = entityManager.createQuery("from Estado", Estado.class);
		return listEstado.getResultList();
	}

	@Override
	public Estado buscarPorId(Long id)
	{
		return entityManager.find(Estado.class, id);
	}

	@Override
	@Transactional
	public Estado salvar(Estado estado)
	{
		return entityManager.merge(estado);
	}

	@Override
	@Transactional
	public void remover(Estado estado)
	{
		estado = buscarPorId(estado.getId());
		entityManager.remove(estado);
	}
}
