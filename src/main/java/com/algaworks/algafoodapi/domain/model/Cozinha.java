package com.algaworks.algafoodapi.domain.model;

import com.algaworks.algafoodapi.Groups;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    @NotNull(groups = Groups.CadastroRestaurante.class)
    private Long id;

    /*@JsonProperty("titulo") //Mudar o nome do atributo na resposta*/
    @Column(nullable = false)
    @NotBlank
    private String nome;

    @JsonIgnore
    @OneToMany(mappedBy = "cozinha")
    private List<Restaurante> restaurantes = new ArrayList<>();

}
