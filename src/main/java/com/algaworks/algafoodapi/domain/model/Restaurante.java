package com.algaworks.algafoodapi.domain.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
import lombok.Data;

@Data
@Entity
@Table(name = "tab_restaurante")
public class Restaurante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(name = "taxa_frete")
    private BigDecimal taxaFrete;

}
