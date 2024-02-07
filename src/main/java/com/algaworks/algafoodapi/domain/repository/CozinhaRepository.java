package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.domain.model.Cozinha;
import java.util.List;

public interface CozinhaRepository
{
	List<Cozinha> listar();
	Cozinha buscar(Long id);
	List<Cozinha> buscarPorNome(String nome);
	Cozinha cadastrar(Cozinha cozinha);
	void remover(Long id);
}
