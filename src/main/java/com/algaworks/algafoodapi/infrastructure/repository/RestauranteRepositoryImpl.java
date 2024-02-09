package com.algaworks.algafoodapi.infrastructure.repository;

import com.algaworks.algafoodapi.domain.model.Restaurante;
import com.algaworks.algafoodapi.domain.repository.RestauranteRepositoryQueries;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.math.BigDecimal;
import java.util.List;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Restaurante> consultar(String nome, BigDecimal taxaFreteInicial,
                                       BigDecimal taxaFreteFinal){
        /*Usando CriteriaAPI*/
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Restaurante> criteriaQuery = criteriaBuilder.createQuery(Restaurante.class);

        criteriaQuery.from(Restaurante.class);

        TypedQuery<Restaurante> query = entityManager.createQuery(criteriaQuery);

        return query.getResultList();
        /*

        FORMA Menos programatica de se fazer:

        var jpql = new StringBuilder();
        jpql.append("from Restaurante where 0 = 0 ");

        var params = new HashMap<String, Object>();

        if (StringUtils.hasLength(nome)){
            jpql.append("and nome like :nome ");
            params.put("nome", "%"+nome+"%");
        }

        if (Objects.nonNull(taxaFreteInicial)){
            jpql.append("and taxaFrete >= :taxaInicial ");
            params.put("taxaInicial", taxaFreteInicial);
        }

        if (Objects.nonNull(taxaFreteFinal)){
            jpql.append("and taxaFrete <= :taxaFinal ");
            params.put("taxaFinal", taxaFreteFinal);
        }

        TypedQuery<Restaurante> query = entityManager.createQuery(jpql.toString(), Restaurante.class);

        params.forEach(query::setParameter);
        // params.forEach((chave, valor) -> query.setParameter(chave, valor));

        return query.getResultList();*/

    }
}
