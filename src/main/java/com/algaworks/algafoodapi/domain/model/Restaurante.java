package com.algaworks.algafoodapi.domain.model;

import com.algaworks.algafoodapi.api.controller.Groups;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
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
//    @NotNull
    @NotBlank(message = "Nome é obrigatório e não pode ser vazio")
    private String nome;

    //@DecimalMin("0")
    @PositiveOrZero
    @Column(name = "taxa_frete", nullable = false)
    private BigDecimal taxaFrete;

    @ManyToOne//(fetch = FetchType.LAZY) // Carrega a cozinha apenas quando for acessada
    // @JsonIgnore
   /* @JsonIgnoreProperties(value = "hibernateLazyInitializer") // Ignora a propriedade hibernateLazyInitializer*/
    @ConvertGroup(from = Default.class, to = Groups.CozinhaId.class)
    @NotNull
    @JoinColumn(name = "cozinha_id", nullable = false)
    @Valid
    private Cozinha cozinha;

    @ManyToMany//(fetch = FetchType.EAGER) Não é muito comum mudar de Lazy para Eager, pois pode causar problemas de performance
    /*@JoinColumn(name = "formaPagamento_id",nullable = false)*/
    /*@JsonIgnore //Queremos usar as formas de pagamentos para resolver o problema do n+1 com fetch join*/
    @JoinTable(name = "restaurante_forma_pagamento",
    joinColumns = @JoinColumn(name = "restaurante_id"),
    inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
    private List<FormaPagamento> formasPagamento = new ArrayList<>();

    @Embedded
    @JsonIgnore
    private Endereco endereco;

    @Column(nullable = false, columnDefinition = "datetime")
    @JsonIgnore
    @CreationTimestamp // Anotação para que o hibernate preencha a data de cadastro
    private LocalDateTime dataCadastro;

    @Column(nullable = false, columnDefinition = "datetime")
    @UpdateTimestamp // Anotação para que o hibernate preencha a data de atualização
    @JsonIgnore
    private LocalDateTime dataAtualizacao;

    @OneToMany(mappedBy = "restaurante")
    @JsonIgnore
    private List<Produto> produtos = new ArrayList<>();
}
