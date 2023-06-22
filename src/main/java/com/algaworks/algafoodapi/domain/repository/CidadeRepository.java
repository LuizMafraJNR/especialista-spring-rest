package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.domain.model.Cidade;
import java.util.List;

public interface CidadeRepository
{
	List<Cidade> listar();
	Cidade buscarPorId(Long id);
	Cidade salvar(Cidade cidade);
	void remover(Cidade cidade);
}
