package com.algaworks.algafoodapi.jpa.cozinha;

import com.algaworks.algafoodapi.AlgafoodApiApplication;
import com.algaworks.algafoodapi.domain.model.Cozinha;
import com.algaworks.algafoodapi.domain.repository.CozinhaRepository;
import com.algaworks.algafoodapi.infrastructure.repository.CozinhaRepositoryImpl;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class InsereCozinhaMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CozinhaRepositoryImpl cadastroCozinha = applicationContext.getBean(
            CozinhaRepositoryImpl.class);

        Cozinha cozinha1 = new Cozinha();
        cozinha1.setNome("Cosmopolitan");
        Cozinha cozinha2 = new Cozinha();
        cozinha2.setNome("Jaquecmads");

         cozinha1 = cadastroCozinha.cadastrar(cozinha1);
         cozinha2 = cadastroCozinha.cadastrar(cozinha2);
        System.out.printf("%d - %s\n", cozinha1.getId(), cozinha1.getNome());
        System.out.printf("%d - %s\n", cozinha2.getId(), cozinha2.getNome());

    }
}
