package br.com.zupacademy.isadora.ecommerce.servicoexterno.pagamento;

import br.com.zupacademy.isadora.ecommerce.pedido.Pedido;
import br.com.zupacademy.isadora.ecommerce.transacao.Transacao;

public interface PagamentoRequest {
    Transacao convert(Pedido pedido);
    Long getTransacaoGatewayId();
}
