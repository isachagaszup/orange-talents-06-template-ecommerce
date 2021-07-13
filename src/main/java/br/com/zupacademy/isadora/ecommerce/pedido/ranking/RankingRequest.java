package br.com.zupacademy.isadora.ecommerce.pedido.ranking;

import javax.validation.constraints.NotNull;

public class RankingRequest {

    @NotNull
    private Long idCompra;
    @NotNull
    private Long idVendedor;

    public RankingRequest(@NotNull Long idCompra, @NotNull Long idVendedor) {
        this.idCompra = idCompra;
        this.idVendedor = idVendedor;
    }

    public Long getIdCompra() {
        return idCompra;
    }

    public Long getIdVendedor() {
        return idVendedor;
    }
}
