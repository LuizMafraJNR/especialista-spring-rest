package com.algaworks.algafoodapi.domain.model;

import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Grupo {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @ManyToMany
    @JoinTable(name = "grupo_permissao",
    joinColumns = @JoinColumn(name = "grupo_id"),
    inverseJoinColumns = @JoinColumn(name = "permissao_id"))
    private Set<Permissao> permissoes = new HashSet<>();

    public Boolean associarPermissao(Permissao permissao) {
        return this.permissoes.add(permissao);
    }

    public Boolean desassociarPermissao(Permissao permissao) {
        return this.permissoes.remove(permissao);
    }
}
