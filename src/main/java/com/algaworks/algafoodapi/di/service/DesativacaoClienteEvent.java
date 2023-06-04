package com.algaworks.algafoodapi.di.service;

import com.algaworks.algafoodapi.di.model.Cliente;

public class DesativacaoClienteEvent {
    private Cliente cliete;

    public DesativacaoClienteEvent(Cliente cliete) {
        this.cliete = cliete;
    }

    public Cliente getCliete() {
        return cliete;
    }
}
