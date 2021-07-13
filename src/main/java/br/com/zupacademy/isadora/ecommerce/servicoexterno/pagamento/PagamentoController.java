package br.com.zupacademy.isadora.ecommerce.servicoexterno.pagamento;

import br.com.zupacademy.isadora.ecommerce.gateway.PagSeguroRequest;
import br.com.zupacademy.isadora.ecommerce.gateway.PayPalRequest;
import br.com.zupacademy.isadora.ecommerce.pedido.Pedido;
import br.com.zupacademy.isadora.ecommerce.pedido.PedidoRepository;
import br.com.zupacademy.isadora.ecommerce.transacao.StatusTransacao;
import br.com.zupacademy.isadora.ecommerce.transacao.Transacao;
import br.com.zupacademy.isadora.ecommerce.transacao.TransacaoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class PagamentoController {
    private final PedidoRepository pedidoRepository;
    private final ProcessaPagamento processaPagamento;
    private final TransacaoRepository transacaoRepository;

    public PagamentoController(PedidoRepository pedidoRepository, ProcessaPagamento processaPagamento, TransacaoRepository transacaoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.processaPagamento = processaPagamento;
        this.transacaoRepository = transacaoRepository;
    }

    @PostMapping("/paypal/{id}")
    public ResponseEntity<?> processaPagamentoPayPal(@PathVariable("id") Long idCompra, @RequestBody @Valid PayPalRequest payPalRequest, UriComponentsBuilder uriLocal){
        return processaPagamento(idCompra, payPalRequest, uriLocal);
    }

    @PostMapping("/pagseguro/{id}")
    public ResponseEntity<?> processaPagamentoPagSeguro(@PathVariable("id") Long idCompra, @RequestBody @Valid PagSeguroRequest pagSeguroRequest, UriComponentsBuilder uriLocal){
        return processaPagamento(idCompra, pagSeguroRequest, uriLocal);
    }

    public ResponseEntity<?> processaPagamento(Long idCompra, PagamentoRequest paymentRequest, UriComponentsBuilder uriLocal){
        Optional<Transacao> optionalTransacao = transacaoRepository.findFirstByTransacaoGatewayIdAndStatusTransacao(paymentRequest.getTransacaoGatewayId(), StatusTransacao.SUCESSO);
        if(optionalTransacao.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Transação já foi efetuada.");
        }
        Optional<Pedido> optionalPedido = pedidoRepository.findById(idCompra);
        if (optionalPedido.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id do pedido inválido.");
        }
        Pedido pedido = optionalPedido.get();
        Transacao transacao = paymentRequest.convert(pedido);
        boolean transacaoAdicionada = pedido.adicionaTransacao(transacao);

        processaPagamento.process(pedido, transacaoAdicionada, uriLocal);

        return ResponseEntity.ok().build();
    }

}
