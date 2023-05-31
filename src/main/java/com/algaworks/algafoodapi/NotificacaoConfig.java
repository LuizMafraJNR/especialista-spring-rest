package com.algaworks.algafoodapi;

import com.algaworks.algafoodapi.di.notificacao.NotificadorEmail;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificacaoConfig {

    @Bean
    /**
     * Anotamos com @Bean para indicar que esse metodo que instancia, configura, inicializa um novo objeto que será gerenciado
     * pelo container Spring.
     */
    public NotificadorEmail notificadorEmail(){
        NotificadorEmail notificadorEmail = new NotificadorEmail("smtp.algawork.com.br");
        notificadorEmail.setCaixaAlta(true);

        return  notificadorEmail;
    }
}
