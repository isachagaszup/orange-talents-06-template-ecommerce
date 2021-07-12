package br.com.zupacademy.isadora.ecommerce.pedido;

import org.springframework.web.util.UriComponentsBuilder;

public enum TipoGatewayPagamento {
    PAGSEGURO {
        String criaPagamento(Pedido pedido, UriComponentsBuilder uriComponentsBuilder) {
            String urlRetornoPagseguro = uriComponentsBuilder
                    .path("/pagseguro/{id}")
                    .buildAndExpand(pedido.getUuid()).toString();

            return "pagseguro.com?returnId=" + pedido.getUuid() + "?redirectUrl="
                    + urlRetornoPagseguro;
        }
    },
    PAYPAL {
        String criaPagamento (Pedido pedido, UriComponentsBuilder uriComponentsBuilder) {
            String urlRetornoPayPal = uriComponentsBuilder
                    .path("/paypal/{id}")
                    .buildAndExpand(pedido.getUuid()).toString();

            return "paypal.com?buyerId=" + pedido.getUuid() + "?redirectUrl="
                    + urlRetornoPayPal;
        }
    };

    abstract String criaPagamento(Pedido pedido, UriComponentsBuilder uriComponentsBuilder);
}
