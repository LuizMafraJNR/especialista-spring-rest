package com.algaworks.algafoodapi.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@JsonRootName("gastronomia") //Mudar o nome do objeto na resposta
/*@Table(name = "tab_cozinha")*/
public class Cozinha {
    @Id
    //@JsonIgnore //Não mostrar o id na resposta
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*@JsonProperty("titulo") //Mudar o nome do atributo na resposta*/
    @Column(nullable = false)
    private String nome;

    /*@JsonIgnore*/
    @OneToMany(mappedBy = "cozinha")
    private List<Restaurante> restaurantes = new ArrayList<>();

}
