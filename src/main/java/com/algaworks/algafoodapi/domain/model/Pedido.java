package com.algaworks.algafoodapi.domain.model;

import com.algaworks.algafoodapi.domain.exception.NegocioException;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pedido {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal subtotal;
    private BigDecimal taxaFrete;
    private BigDecimal valorTotal;

    @Embedded
    private Endereco enderecoEntrega;
    @Enumerated(EnumType.STRING)
    private StatusPedido status = StatusPedido.CRIADO;

    @CreationTimestamp
    private OffsetDateTime dataCriacao;

    private OffsetDateTime dataConfirmacao;
    private OffsetDateTime dataCancelamento;
    private OffsetDateTime dataEntrega;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private FormaPagamento formaPagamento;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Restaurante restaurante;

    @ManyToOne
    @JoinColumn(name = "usuario_cliente_id", nullable = false)
    private Usuario cliente;

    /**
     * Relacionamento One-to-Many com ItemPedido.
     * O CascadeType.ALL propaga todas as operações de persistência (persist, merge, remove, etc.)
     * do Pedido para os ItemPedido associados, garantindo que itens sejam salvos, atualizados
     * ou excluídos automaticamente junto com o pedido, evitando gerenciamento manual e erros.
     */
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itens = new ArrayList<>();

    public void calcularValorTotal() {
        getItens().forEach(ItemPedido::calcularPrecoTotal);

        this.subtotal = getItens().stream()
            .map(item -> item.getPrecoTotal())
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.valorTotal = this.subtotal.add(this.taxaFrete);
    }

    public void definirFrete() {
        setTaxaFrete(getRestaurante().getTaxaFrete());
    }

    public void atribuirPedidoAosItens() {
        this.itens.forEach(item -> item.setPedido(this));
    }

    public void confirmar() {
        if(!getStatus().equals(StatusPedido.CRIADO)) {
            throw new NegocioException(String.format("Pedido com status %s não pode ser confirmado.",
                getStatus()));
        }
        setStatus(StatusPedido.CONFIRMADO);
        setDataConfirmacao(OffsetDateTime.now());
    }

    public void entregar() {
        if(!getStatus().equals(StatusPedido.CONFIRMADO)) {
            throw new NegocioException(String.format("Pedido com status %s não pode ser entregue.",
                getStatus()));
        }
        setStatus(StatusPedido.ENTREGUE);
        setDataEntrega(OffsetDateTime.now());
    }

    public void cancelar() {
        if(!getStatus().equals(StatusPedido.CRIADO)) {
            throw new NegocioException(String.format("Pedido com status %s não pode ser cancelado.",
                getStatus()));
        }
        setStatus(StatusPedido.CANCELADO);
        setDataCancelamento(OffsetDateTime.now());
    }
}
