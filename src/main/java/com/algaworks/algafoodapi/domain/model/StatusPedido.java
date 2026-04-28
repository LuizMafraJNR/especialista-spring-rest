package com.algaworks.algafoodapi.domain.model;

import java.util.Arrays;
import java.util.List;

public enum StatusPedido {
    CRIADO("Criado"),
    CONFIRMADO("Confirmado", CRIADO),
    ENTREGUE("Entregue", CONFIRMADO),
    CANCELADO("Cancelado", CRIADO);

    private String descricao;
    private List<StatusPedido> statusAnteriores;

    /*
    Varargs (ou argumentos variáveis) é um recurso do Java que permite que um método receba um
    número variável de argumentos. Isso simplifica a chamada de métodos que precisam lidar com
    múltiplos parâmetros, eliminando a necessidade de criar manualmente arrays para passar os
    valores.

    Como Declarar e Usar Varargs

    Para declarar um parâmetro varargs, utiliza-se três pontos (...) após o tipo do parâmetro.
    O parâmetro varargs deve ser sempre o último na lista de parâmetros do método.
    Por exemplo:
    */
    StatusPedido(String descricao, StatusPedido... statusAnteriores) {
        this.descricao = descricao;
        this.statusAnteriores = Arrays.asList(statusAnteriores);
    }

    public String getDescricao()
    {
        return this.descricao;
    }

    public boolean podeSerAlteradoPara(StatusPedido novoStatus) {
        return novoStatus.statusAnteriores.contains(this);
    }
}
