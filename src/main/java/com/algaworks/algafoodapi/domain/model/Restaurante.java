package com.algaworks.algafoodapi.domain.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

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
    @JsonIgnore
    @JoinColumn(name = "cozinha_codigo", nullable = false)
    private Cozinha cozinha;
    @ManyToOne
    @JoinColumn(name = "formaPagamento_id",nullable = false)
    private FormaPagamento formaPagamento;
}
