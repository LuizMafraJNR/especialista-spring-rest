package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.domain.model.Restaurante;

import java.math.BigDecimal;
import java.util.List;

public interface RestauranteRepositoryQueries {
    List<Restaurante> consultar(String nome, BigDecimal taxaFreteInicial,
                                BigDecimal taxaFreteFinal);
    List<Restaurante> comFreteGratisPorNome(String nome);
}
