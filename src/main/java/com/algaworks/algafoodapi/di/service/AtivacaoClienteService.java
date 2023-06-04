package com.algaworks.algafoodapi.di.service;

import com.algaworks.algafoodapi.di.model.Cliente;
import com.algaworks.algafoodapi.di.notificacao.Notificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AtivacaoClienteService {
    @Autowired // 3 forma
    private Notificador notificador;
//
//    @Autowired
     // Primeira forma pelo construtor (AutoWired opcional)
//    public AtivacaoClienteService(Notificador notificador) {
//        this.notificador = notificador;
//        System.out.println("Ativação" + notificador);
//    }

//    public AtivacaoClienteService(String qualquer){
//
//    }

    public void ativar(Cliente cliente){
        cliente.setAtivo(true);
        notificador.notificar(cliente, "Seu cadastro no sistema está ativo.");
    }

//    @Autowired // Segunda forma
//    public void setNotificador(Notificador notificador) {
//        this.notificador = notificador;
//    }
}
