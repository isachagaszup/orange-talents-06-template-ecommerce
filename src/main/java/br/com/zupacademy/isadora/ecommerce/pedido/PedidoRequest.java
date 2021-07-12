package br.com.zupacademy.isadora.ecommerce.pedido;

import br.com.zupacademy.isadora.ecommerce.produto.Produto;
import br.com.zupacademy.isadora.ecommerce.produto.ProdutoRepository;
import br.com.zupacademy.isadora.ecommerce.usuario.Usuario;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class PedidoRequest {

    @NotNull @Positive
    private Integer quantidade;
    @NotNull
    private Long produtoId;
    @NotNull
    private TipoGatewayPagamento gateway;

    public PedidoRequest() {}

    public PedidoRequest(@Positive Integer quantidade, @NotNull Long produtoId, @NotNull TipoGatewayPagamento gateway) {
        this.quantidade = quantidade;
        this.produtoId = produtoId;
        this.gateway = gateway;
    }

    public Pedido toModel(Usuario comprador, ProdutoRepository produtoRepository) {
        Produto produto = produtoRepository.getById(produtoId);
        produto.abateEstoque(quantidade);

        return new Pedido(produto, quantidade, comprador, gateway);
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public TipoGatewayPagamento getGateway() {
        return gateway;
    }
}
