package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long>
{
    List<Restaurante> getRestauranteByTaxaFreteBetween(BigDecimal valorInicial,
                                                       BigDecimal valorFinal);
    List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinha);
}
