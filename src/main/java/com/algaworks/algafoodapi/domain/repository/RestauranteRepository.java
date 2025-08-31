package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.domain.model.Restaurante;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestauranteRepository extends
        CustomJpaRepository<Restaurante, Long>, RestauranteRepositoryQueries, JpaSpecificationExecutor<Restaurante>
{
    /*
    * Se um restaurante não tiver nenhuma forma de pagamento associada a ele, ele não será retornado
    * Para resolver isso, podemos usar o LEFT JOIN FETCH para trazer os restaurantes mesmo que não tenham formas de pagamento associadas
    *
    *
    * Acontece que o relacionamento entre restaurante e forma de pagamento é muitos pra muitos, ou seja, um restaurante pode ter várias formas de pagamento,
    *  e uma forma de pagamento pode estar em vários restaurantes.
    * Se você tem um restaurante com mais de uma forma de pagamento associada e faz o select no banco (com o join entre as tabelas)
    * os dados do restaurante vão se repetir para cada forma de pagamento. O problema é que isto está se refletindo na aplicação,
    * o que na aula foi explicado que seria resolvido pelo hibernate, mas não está ocorrendo.
    * */
    @Query("select distinct r from Restaurante r join r.cozinha left join fetch r.formasPagamento")
    List<Restaurante> findAll();

    List<Restaurante> getRestauranteByTaxaFreteBetween(BigDecimal valorInicial,
                                                       BigDecimal valorFinal);

    /*@Query("from Restaurante where nome like %:nome% and cozinha.id = :id")*/
    List<Restaurante> consultarPorNome(String nome,@Param("id")Long cozinhaId);

    Optional<Restaurante> findFirstByNomeContaining(String nome);

    @Query("from Restaurante where nome like %:nome%")
    List<Restaurante> buscarTop2PorNome(String nome, Pageable pageable);

    int countByCozinhaId(Long cozinhaId);
}
