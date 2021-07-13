package br.com.zupacademy.isadora.ecommerce.pedido;

import org.springframework.web.util.UriComponentsBuilder;

public enum TipoGatewayPagamento {

    PAYPAL("paypal.com/", "/pagamentos/compras/{id}/paypal"),
    PAGSEGURO("pagseguro.com/", "/pagamentos/compras/{id}/pagseguro");

    private String url;
    private String retorno;

    TipoGatewayPagamento(String url, String retorno) {
        this.url = url;
        this.retorno = retorno;
    }

    public String criaUrlRetorno(Pedido pedido, UriComponentsBuilder uriComponentsBuilder){
        String urlRetorno = uriComponentsBuilder
                .path(retorno).buildAndExpand(pedido.getId())
                .toString();

        return url + pedido.getUuid() + "?redirectUrl=" + urlRetorno;
    }
}
