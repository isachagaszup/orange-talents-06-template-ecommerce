package br.com.zupacademy.isadora.ecommerce.gateway;

import br.com.zupacademy.isadora.ecommerce.pedido.Pedido;
import br.com.zupacademy.isadora.ecommerce.servicoexterno.pagamento.PagamentoRequest;
import br.com.zupacademy.isadora.ecommerce.transacao.StatusTransacao;
import br.com.zupacademy.isadora.ecommerce.transacao.Transacao;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class PayPalRequest implements PagamentoRequest {
    @NotNull
    private Long transacaoGatewayId;
    @NotNull
    @Min(0)
    @Max(1)
    private Integer status;

    public PayPalRequest(@NotNull Long transacaoGatewayId, @NotNull @Min(0) @Max(1) Integer status) {
        this.transacaoGatewayId = transacaoGatewayId;
        this.status = status;
    }

    @Override
    public Transacao convert(Pedido pedido) {
        StatusTransacao statusTransacao = StatusTransacao.get(status);
        return new Transacao(transacaoGatewayId, statusTransacao, pedido);
    }

    @Override
    public Long getTransacaoGatewayId() {
        return transacaoGatewayId;
    }
}
