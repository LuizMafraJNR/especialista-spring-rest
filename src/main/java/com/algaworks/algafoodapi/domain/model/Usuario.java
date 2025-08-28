package com.algaworks.algafoodapi.domain.model;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable= false)
    private String nome;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String senha;
    @Column(nullable = false, columnDefinition = "datetime")
    @CreationTimestamp
    private OffsetDateTime dataCadastro;
    @ManyToMany
    @JoinTable(name = "usuario_grupo",
    joinColumns = @JoinColumn(name = "usuario_id"),
    inverseJoinColumns = @JoinColumn(name = "grupo_id"))
    private List<Grupo> grupos = new ArrayList<>();
}
