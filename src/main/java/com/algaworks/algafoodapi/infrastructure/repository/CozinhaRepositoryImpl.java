package com.algaworks.algafoodapi.infrastructure.repository;

import com.algaworks.algafoodapi.domain.model.Cozinha;
import com.algaworks.algafoodapi.domain.repository.CozinhaRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CozinhaRepositoryImpl implements CozinhaRepository
{
	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public List<Cozinha> listar() {
		TypedQuery<Cozinha> query = entityManager.createQuery("from Cozinha", Cozinha.class);
		return query.getResultList();
	}
	@Override
	@Transactional
	public Cozinha cadastrar(Cozinha cozinha){
		return entityManager.merge(cozinha);
	}
	@Override
	public Cozinha buscar(Long id){
		return entityManager.find(Cozinha.class, id);
	}
	@Override
	@Transactional
	public void remover(Cozinha cozinha){
		cozinha = buscar(cozinha.getId());
		entityManager.remove(cozinha);
	}
}
