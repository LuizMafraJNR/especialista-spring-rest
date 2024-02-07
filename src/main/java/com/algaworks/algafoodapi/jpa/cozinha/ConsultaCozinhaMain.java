package com.algaworks.algafoodapi.jpa.cozinha;

import com.algaworks.algafoodapi.AlgafoodApiApplication;
import com.algaworks.algafoodapi.domain.model.Cozinha;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class ConsultaCozinhaMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CozinhaRepositoryImpl cadastroCozinha = applicationContext.getBean(CozinhaRepositoryImpl.class);
        List<Cozinha> list = cadastroCozinha.listar();
        for(Cozinha lista : list){
            System.out.println(lista.getNome());
        }

    }
}
