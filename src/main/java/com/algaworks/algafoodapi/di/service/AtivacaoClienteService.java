package com.algaworks.algafoodapi.di.service;

import com.algaworks.algafoodapi.di.model.Cliente;
import com.algaworks.algafoodapi.di.notificacao.NotificadorEmail;
import org.springframework.stereotype.Component;

@Component
public class AtivacaoClienteService {
    private NotificadorEmail notificadorEmail;

    public void ativar(Cliente cliente){
        cliente.setAtivo(true);
        notificadorEmail.notificar(cliente, "Seu cadastro no sistema está ativo.");
    }
}
