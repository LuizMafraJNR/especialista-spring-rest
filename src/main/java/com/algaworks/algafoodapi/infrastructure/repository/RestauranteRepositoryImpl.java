package com.algaworks.algafoodapi.infrastructure.repository;

import com.algaworks.algafoodapi.domain.model.Restaurante;
import com.algaworks.algafoodapi.domain.repository.RestauranteRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class RestauranteRepositoryImpl implements RestauranteRepository

{

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Restaurante> listar()
	{
		TypedQuery<Restaurante> query = entityManager.createQuery("from Restaurante", Restaurante.class);
		return query.getResultList();
	}

	@Override
	public Restaurante buscarId(Long id)
	{
		return entityManager.find(Restaurante.class, id);
	}

	@Override
	@Transactional
	public Restaurante cadastrar(Restaurante restaurante)
	{
		return entityManager.merge(restaurante);
	}

	@Override
	@Transactional
	public void remover(Restaurante restaurante)
	{
		restaurante = buscarId(restaurante.getId());
		entityManager.remove(restaurante);
	}
}
