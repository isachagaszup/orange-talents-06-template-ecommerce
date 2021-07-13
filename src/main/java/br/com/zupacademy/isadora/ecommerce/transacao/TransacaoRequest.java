package br.com.zupacademy.isadora.ecommerce.transacao;

import br.com.zupacademy.isadora.ecommerce.pedido.Pedido;
import br.com.zupacademy.isadora.ecommerce.pedido.PedidoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public class TransacaoRequest {
    public Transacao toModel(PedidoRepository pedidoRepository, TransacaoRepository transacaoRepository, Long id, StatusTransacao statusTransacao, Long transacaoGatewayId) {
        Optional<Pedido> optionalPedido = pedidoRepository.findById(id);

        if (optionalPedido.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado!");
        }

        Optional<Transacao> optionalTransacao = transacaoRepository.findFirstByTransacaoGatewayIdAndStatusTransacao(transacaoGatewayId, StatusTransacao.SUCESSO);
        if (optionalTransacao.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Transação já foi concluída com sucesso!");
        }

        Pedido pedido = optionalPedido.get();
        return new Transacao(transacaoGatewayId, statusTransacao, pedido);
    }
}
