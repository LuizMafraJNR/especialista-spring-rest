package com.algaworks.algafoodapi.domain.model;

import javax.persistence.*;
import java.util.Objects;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "tab_cozinha")
public class Cozinha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "col_nome", nullable = false)
    private String nome;

}
