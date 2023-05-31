package com.algaworks.algafoodapi.di.notificacao;

import com.algaworks.algafoodapi.di.model.Cliente;
public class NotificadorEmail implements Notificador {
    private boolean caixaAlta;
    private String hostServidorSmtp;
    public NotificadorEmail(String hostServidorSmtp){
        /**
         * Tem alguns momentos que a gente quer editar a instanciação do bean, não simplesmente dar um new Instace.
         * Criar um novo bean e deixar disponivel no container do Spring.
         */
        this.hostServidorSmtp = hostServidorSmtp;
        System.out.println("Notificador Email");
    }
    @Override
    public void notificar(Cliente cliente, String mensagem){
        if (this.caixaAlta){
            mensagem = mensagem.toUpperCase();
        }
        System.out.printf("Notificando %s atraves do e-mail %s: %s\n", cliente.getNome(), cliente.getEmail(), mensagem);
    }

    public void setCaixaAlta(boolean caixaAlta) {
        this.caixaAlta = caixaAlta;
    }
}
