package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.domain.model.Restaurante;
import java.util.List;

public interface RestauranteRepository
{
	List<Restaurante> listar();
	Restaurante buscarId(Long id);
	Restaurante cadastrar(Restaurante restaurante);
	void remover(Restaurante restaurante);
}
