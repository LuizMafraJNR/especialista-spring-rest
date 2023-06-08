package com.algaworks.algafoodapi.di.notificacao;

import com.algaworks.algafoodapi.di.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
@Profile("Production")
@TipoDoNotificador(NivelUrgencia.NORMAL)
@Component
public class NotificadorEmail implements Notificador {
////    @Value("${notificador.email.host-servidor}")
//    private String host;
////    @Value("${notificador.email.porta-servidor}")
//    private Integer porta;
    @Autowired
    private NotificadorProperties notificadorProperties;

    public NotificadorEmail() {
        System.out.println("Notificador email REAL.");

    }

    @Override
    public void notificar(Cliente cliente, String mensagem){
        System.out.printf("Notificando %s atraves do e-mail %s: %s\n", cliente.getNome(), cliente.getEmail(), mensagem);
        System.out.println("Servidor email: "+notificadorProperties.getHostServidor());
        System.out.println("Porta servidor: "+notificadorProperties.getPorta());
    }

}
