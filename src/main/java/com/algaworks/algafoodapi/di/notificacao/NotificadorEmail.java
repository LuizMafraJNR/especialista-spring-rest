package com.algaworks.algafoodapi.di.notificacao;

import com.algaworks.algafoodapi.di.model.Cliente;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
@TipoDoNotificador(NivelUrgencia.NORMAL)
@Component
public class NotificadorEmail implements Notificador {

    @Override
    public void notificar(Cliente cliente, String mensagem){
        System.out.printf("Notificando %s atraves do e-mail %s: %s\n", cliente.getNome(), cliente.getEmail(), mensagem);
    }

}
