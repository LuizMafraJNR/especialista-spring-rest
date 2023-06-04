package com.algaworks.algafoodapi.listener;

import com.algaworks.algafoodapi.di.service.AtivacaoClienteService;
import com.algaworks.algafoodapi.di.service.ClienteAtivadoEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EmitirNotaFiscalAtivacaoListener {

    @EventListener
    public void emitirNotaFiscalAtivacao(ClienteAtivadoEvent event){
        System.out.println("Nota Fiscal do "+event.getCliente().getNome()+" emitida.");
    }
}
