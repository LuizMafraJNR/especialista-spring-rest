package com.algaworks.algafoodapi.di.notificacao;

import com.algaworks.algafoodapi.di.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
@Profile("Developer")
@TipoDoNotificador(NivelUrgencia.NORMAL)
@Component
public class NotificadorEmailMOCK implements Notificador {

    @Autowired
    private NotificadorProperties notificadorProperties;

    public NotificadorEmailMOCK() {
        System.out.println("NotificadorEmail MOCK.");
    }

    @Override
    public void notificar(Cliente cliente, String mensagem){
        System.out.printf("MOCK: Notificando %s atraves do e-mail %s: %s\n", cliente.getNome(), cliente.getEmail(), mensagem);
        System.out.println("Servidor email: "+notificadorProperties.getHostServidor());
        System.out.println("Porta servidor: "+notificadorProperties.getPorta());
    }

}
