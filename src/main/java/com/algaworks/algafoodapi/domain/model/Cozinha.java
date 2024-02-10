package com.algaworks.algafoodapi.domain.model;

import javax.persistence.*;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@JsonRootName("gastronomia") //Mudar o nome do objeto na resposta
@Table(name = "tab_cozinha")
public class Cozinha {
    @Id
    //@JsonIgnore //Não mostrar o id na resposta
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*@JsonProperty("titulo") //Mudar o nome do atributo na resposta*/
    @Column(nullable = false)
    private String nome;

}
