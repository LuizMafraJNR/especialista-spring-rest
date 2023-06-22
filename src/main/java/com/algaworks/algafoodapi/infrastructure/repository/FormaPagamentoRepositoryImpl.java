package com.algaworks.algafoodapi.infrastructure.repository;

import com.algaworks.algafoodapi.domain.model.FormaPagamento;
import com.algaworks.algafoodapi.domain.repository.FormaPagamentoRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class FormaPagamentoRepositoryImpl implements FormaPagamentoRepository
{

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<FormaPagamento> listar()
	{
		TypedQuery<FormaPagamento> listFormaPagamento = entityManager.createQuery("from FormaPagamento", FormaPagamento.class);
		return listFormaPagamento.getResultList();
	}

	@Override
	public FormaPagamento buscarPorId(Long id)
	{
		return entityManager.find(FormaPagamento.class, id);
	}

	@Transactional
	@Override
	public FormaPagamento salvar(FormaPagamento formaPagamento)
	{
		return entityManager.merge(formaPagamento);
	}

	@Transactional
	@Override
	public void remover(FormaPagamento formaPagamento)
	{
		formaPagamento = buscarPorId(formaPagamento.getId());
		entityManager.remove(formaPagamento);
	}
}
