package com.algaworks.algafoodapi.di.service;

import com.algaworks.algafoodapi.di.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class AtivacaoClienteService {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void ativar(Cliente cliente){
        cliente.setAtivo(true);

        // Dizer para o container que o cliente está ativo neste momento.
        applicationEventPublisher.publishEvent(new ClienteAtivadoEvent(cliente));
    }

    public void desativar(Cliente cliente){
        cliente.setAtivo(false);

        applicationEventPublisher.publishEvent(new DesativacaoClienteEvent(cliente));
    }

}
