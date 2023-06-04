package com.algaworks.algafoodapi.listener;

import com.algaworks.algafoodapi.di.service.DesativacaoClienteEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DesativaClienteListener {

    @EventListener
    public void desativaCliente(DesativacaoClienteEvent event){
        System.out.println("Cliente "+event.getCliete().getNome()+" desativado com sucesso.");
    }
}
