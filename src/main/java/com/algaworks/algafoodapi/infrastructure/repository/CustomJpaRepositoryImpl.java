package com.algaworks.algafoodapi.infrastructure.repository;

import com.algaworks.algafoodapi.domain.repository.CustomJpaRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.util.Optional;

public class CustomJpaRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID>
        implements CustomJpaRepository<T, ID> {

    private EntityManager manager;
    public CustomJpaRepositoryImpl(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);

        this.manager = em;
    }

    @Override
    public Optional buscarPrimeiro() {
        var jpql = "from "+getDomainClass().getName();
        T entity = manager.createQuery(jpql, getDomainClass())
                .setMaxResults(1)
                .getSingleResult();
        return Optional.ofNullable(entity);
    }
}
