package br.com.zupacademy.isadora.ecommerce.servicoexterno.pagamento;

import br.com.zupacademy.isadora.ecommerce.pedido.*;
import br.com.zupacademy.isadora.ecommerce.servicoexterno.SenderEmail;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Set;

@Component
public class ProcessaPagamento {
    private final PedidoRepository pedidoRepository;
    private final Set<EventoCompraSucesso> eventoCompraSucesso;
    private final SenderEmail senderEmail;

    public ProcessaPagamento(PedidoRepository pedidoRepository, Set<EventoCompraSucesso> eventoCompraSucesso, SenderEmail senderEmail) {
        this.pedidoRepository = pedidoRepository;
        this.eventoCompraSucesso = eventoCompraSucesso;
        this.senderEmail = senderEmail;
    }

    public void process(Pedido pedido, boolean transacaoAdicionada, UriComponentsBuilder uriLocal){
        pedido = pedidoRepository.save(pedido);

        if(transacaoAdicionada && pedido.processadaComSucesso()){
            Pedido finalCompra = pedido;
            eventoCompraSucesso.forEach(evento -> evento.process(finalCompra));
        }else if(!pedido.processadaComSucesso()){
            TipoGatewayPagamento gatewayPagamento = pedido.getGateway();
            senderEmail.emailPagamentoFalhou(pedido, gatewayPagamento.criaUrlRetorno(pedido, uriLocal));
        }
    }
}
