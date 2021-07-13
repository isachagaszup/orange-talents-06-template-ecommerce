package br.com.zupacademy.isadora.ecommerce.pedido.notafiscal;

import javax.validation.constraints.NotNull;

public class NotaFiscalRequest {

    @NotNull
    private Long idCompra;
    @NotNull
    private Long idUsuario;

    public NotaFiscalRequest(@NotNull Long idCompra, @NotNull Long idUsuario) {
        this.idCompra = idCompra;
        this.idUsuario = idUsuario;
    }

    public Long getIdCompra() {
        return idCompra;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }
}
