package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.domain.model.Permissao;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Long>
{
}
