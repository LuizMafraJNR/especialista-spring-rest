package com.algaworks.algafoodapi;

import com.algaworks.algafoodapi.di.notificacao.NotificadorEmail;
import com.algaworks.algafoodapi.di.service.AtivacaoClienteService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*@Configuration*/
/**
 * Objetivo principal do @Configuration = Definição de Beans
 */
public class AlgaConfig {

    /**
     * Anotamos com @Bean para indicar que esse metodo que instancia, configura, inicializa um novo objeto que será gerenciado
     * pelo container Spring.
     */
//    @Bean
    public NotificadorEmail notificadorEmail(){
        NotificadorEmail notificadorEmail = new NotificadorEmail("smtp.algawork.com.br");
        notificadorEmail.setCaixaAlta(true);

        return  notificadorEmail;
    }

//    @Bean
    public AtivacaoClienteService ativacaoClienteService(){
        return new AtivacaoClienteService(notificadorEmail());
    }
}
