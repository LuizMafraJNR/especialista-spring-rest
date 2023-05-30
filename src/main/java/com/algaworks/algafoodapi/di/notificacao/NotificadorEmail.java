package com.algaworks.algafoodapi.di.notificacao;

import com.algaworks.algafoodapi.di.model.Cliente;
import org.springframework.stereotype.Component;
@Component
public class NotificadorEmail implements Notificador {
    public NotificadorEmail(){
        System.out.println("Construtor chamado.");
    }
    @Override
    public void notificar(Cliente cliente, String mensagem){
        System.out.printf("Notificando %s atraves do e-mail %s: %s\n", cliente.getNome(), cliente.getEmail(), mensagem);
    }
}
