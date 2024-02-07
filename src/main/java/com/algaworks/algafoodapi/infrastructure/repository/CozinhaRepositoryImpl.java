package com.algaworks.algafoodapi.infrastructure.repository;

import com.algaworks.algafoodapi.domain.model.Cozinha;
import com.algaworks.algafoodapi.domain.repository.CozinhaRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
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
	public List<Cozinha> buscarPorNome(String nome) {
		return entityManager.createQuery("from Cozinha where nome like :nome",Cozinha.class)
				.setParameter("nome", "%"+ nome + "%")
				.getResultList();
	}

	@Override
	@Transactional
	public void remover(Long id){
		Cozinha cozinha = buscar(id);
		if (cozinha == null){
			throw new EmptyResultDataAccessException(1);
		}
		entityManager.remove(cozinha);
	}
}
