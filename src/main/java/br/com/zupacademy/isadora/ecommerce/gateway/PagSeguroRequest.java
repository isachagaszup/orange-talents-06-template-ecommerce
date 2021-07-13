package br.com.zupacademy.isadora.ecommerce.gateway;

import br.com.zupacademy.isadora.ecommerce.pedido.Pedido;
import br.com.zupacademy.isadora.ecommerce.servicoexterno.pagamento.PagamentoRequest;
import br.com.zupacademy.isadora.ecommerce.transacao.StatusTransacao;
import br.com.zupacademy.isadora.ecommerce.transacao.Transacao;


import javax.validation.constraints.NotNull;

public class PagSeguroRequest implements PagamentoRequest {

    @NotNull
    private Long transacaoGatewayId;
    private PagSeguroPayStatus status;

    public PagSeguroRequest(@NotNull Long transacaoGatewayId, PagSeguroPayStatus status) {
        this.transacaoGatewayId = transacaoGatewayId;
        this.status = status;
    }

    @Override
    public Transacao convert(Pedido pedido) {
        StatusTransacao statusTransacao = status.convert();
        return new Transacao(transacaoGatewayId, statusTransacao, pedido);
    }

    @Override
    public Long getTransacaoGatewayId() {
        return transacaoGatewayId;
    }
}
