package com.algaworks.algafoodapi.di.service;

import com.algaworks.algafoodapi.di.model.Cliente;
import com.algaworks.algafoodapi.di.notificacao.Notificador;
import com.algaworks.algafoodapi.di.notificacao.NotificadorEmail;
import org.springframework.stereotype.Component;

@Component
public class AtivacaoClienteService {
    private Notificador notificador;

    public AtivacaoClienteService(Notificador notificador) {
        this.notificador = notificador;
        System.out.println("Ativação" + notificador);
    }

    public void ativar(Cliente cliente){
        cliente.setAtivo(true);
        notificador.notificar(cliente, "Seu cadastro no sistema está ativo.");
    }


}
