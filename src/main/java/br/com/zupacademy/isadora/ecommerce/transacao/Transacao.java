package br.com.zupacademy.isadora.ecommerce.transacao;

import br.com.zupacademy.isadora.ecommerce.pedido.Pedido;

import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

@Entity
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @PastOrPresent
    private LocalDateTime criadoEm = LocalDateTime.now();
    private Long transacaoGatewayId;
    @Enumerated(value = EnumType.STRING)
    private StatusTransacao statusTransacao;
    @ManyToOne
    private Pedido pedido;

    /**
     * hibernate only
     */
    @Deprecated
    public Transacao() {
    }

    public Transacao(Long transacaoGatewayId, StatusTransacao statusTransacao, Pedido pedido) {
        this.transacaoGatewayId = transacaoGatewayId;
        this.statusTransacao = statusTransacao;
        this.pedido = pedido;
    }

    public boolean comSucesso() {
        return this.statusTransacao.equals(StatusTransacao.SUCESSO);
    }

    public Pedido getPedido() {
        return pedido;
    }

    public StatusTransacao getStatusTransacao() {
        return statusTransacao;
    }
}
