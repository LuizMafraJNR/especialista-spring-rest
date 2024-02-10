package com.algaworks.algafoodapi.infrastructure.repository;

import com.algaworks.algafoodapi.domain.model.Restaurante;
import com.algaworks.algafoodapi.domain.repository.RestauranteRepository;
import com.algaworks.algafoodapi.domain.repository.RestauranteRepositoryQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.algaworks.algafoodapi.infrastructure.repository.spec.RestauranteSpecifications.comFreteGratis;
import static com.algaworks.algafoodapi.infrastructure.repository.spec.RestauranteSpecifications.comNomeSemelhante;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    /*Para não dar erro de Bean Circular(essa IMPL já vai ser usado pelo repository por isso da dando erro)
    * Utilizamos a anotação @Lazy para que ela seja instanciada só quando for necessario. (Preguiçoso).*/
    @Lazy
    private RestauranteRepository restauranteRepository;

    @Override
    public List<Restaurante> consultar(String nome, BigDecimal taxaFreteInicial,
                                       BigDecimal taxaFreteFinal){
        /*Usando CriteriaAPI*/
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Restaurante> criteriaQuery = criteriaBuilder.createQuery(Restaurante.class);
        Root<Restaurante> root = criteriaQuery.from(Restaurante.class);

        List<Predicate> predicates = new ArrayList<>();

        if (StringUtils.hasLength(nome)) {
            predicates.add(criteriaBuilder.like(root.get("nome"), "%" + nome + "%"));
        }
        if (Objects.nonNull(taxaFreteInicial)) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteInicial));
        }
        if (Objects.nonNull(taxaFreteFinal)) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("taxaFrete"), taxaFreteFinal));
        }
        criteriaQuery.where(predicates.toArray(new Predicate[0]));
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

    @Override
    public List<Restaurante> comFreteGratisPorNome(String nome) {
        return restauranteRepository.findAll(comFreteGratis()
                .and(comNomeSemelhante(nome)));
    }
}
