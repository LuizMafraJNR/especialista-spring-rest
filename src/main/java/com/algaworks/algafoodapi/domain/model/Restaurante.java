package com.algaworks.algafoodapi.domain.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@Entity
/*@Table(name = "tab_restaurante")*/
public class Restaurante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(name = "taxa_frete", nullable = false)
    private BigDecimal taxaFrete;
    @ManyToOne
    /*@JsonIgnore*/
    @JoinColumn(name = "cozinha_id", nullable = false)
    private Cozinha cozinha;
    @ManyToMany
    /*@JoinColumn(name = "formaPagamento_id",nullable = false)*/
    @JsonIgnore
    @JoinTable(name = "restaurante_forma_pagamento",
    joinColumns = @JoinColumn(name = "restaurante_id"),
    inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
    private List<FormaPagamento> formasPagamento = new ArrayList<>();
    @Embedded
    private Endereco endereco;
    @Column(nullable = false, columnDefinition = "datetime")
    @CreationTimestamp // Anotação para que o hibernate preencha a data de cadastro
    private LocalDateTime dataCadastro;
    @Column(nullable = false, columnDefinition = "datetime")
    @UpdateTimestamp // Anotação para que o hibernate preencha a data de atualização
    private LocalDateTime dataAtualizacao;
}
