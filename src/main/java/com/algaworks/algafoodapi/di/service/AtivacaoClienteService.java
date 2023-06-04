package com.algaworks.algafoodapi.di.service;

import com.algaworks.algafoodapi.di.model.Cliente;
import com.algaworks.algafoodapi.di.notificacao.Notificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AtivacaoClienteService {
    @Qualifier("sms") // basta aqui apenas para mudar para email ou sms.
    @Autowired(required = false) // 3 forma
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
        if (notificador != null){
            notificador.notificar(cliente,"Seu cadastro no sistema está ativo");
        } else {
            System.out.println("Não existe notificador, mas cliente foi ativado.");
        }
    }

//    @Autowired // Segunda forma
//    public void setNotificador(Notificador notificador) {
//        this.notificador = notificador;
//    }
}
