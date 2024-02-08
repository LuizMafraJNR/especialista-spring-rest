package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.domain.model.Restaurante;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long>
{
    List<Restaurante> getRestauranteByTaxaFreteBetween(BigDecimal valorInicial,
                                                       BigDecimal valorFinal);
    /*@Query("from Restaurante where nome like %:nome% and cozinha.id = :id")*/
    List<Restaurante> consultarPorNome(String nome,@Param("id")Long cozinhaId);

    Optional<Restaurante> findFirstByNomeContaining(String nome);
    @Query("from Restaurante where nome like %:nome%")
    List<Restaurante> buscarTop2PorNome(String nome, Pageable pageable);
    int countByCozinhaId(Long cozinhaId);
}
