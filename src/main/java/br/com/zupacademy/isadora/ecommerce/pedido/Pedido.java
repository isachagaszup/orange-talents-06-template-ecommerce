package br.com.zupacademy.isadora.ecommerce.pedido;

import br.com.zupacademy.isadora.ecommerce.produto.Produto;
import br.com.zupacademy.isadora.ecommerce.transacao.Transacao;
import br.com.zupacademy.isadora.ecommerce.usuario.Usuario;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
public class Pedido {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid = UUID.randomUUID();
    @ManyToOne
    private Produto produto;
    @NotNull @Positive
    private Integer quantidade;
    @NotNull
    private BigDecimal preco;
    @ManyToOne
    private Usuario comprador;
    @Enumerated(value = EnumType.STRING)
    private StatusCompra status = StatusCompra.INICIADA;
    @Enumerated(value = EnumType.STRING)
    private TipoGatewayPagamento gateway;
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<Transacao> transacoes;


    /**
     * hibernate only
     */
    @Deprecated
    public Pedido() {
    }

    public Pedido(Produto produto, @NotNull @Positive Integer quantidade, Usuario comprador, TipoGatewayPagamento gateway) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.preco = produto.getPreco();
        this.comprador = comprador;
        this.gateway = gateway;
    }

    public Long getId() { return id; }

    public Usuario getComprador() {
        return comprador;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getRedirectUrl(UriComponentsBuilder uriComponentsBuilder) {
        return this.gateway.criaUrlRetorno(this, uriComponentsBuilder);
    }

    public TipoGatewayPagamento getGateway() {
        return gateway;
    }

    public boolean adicionaTransacao(Transacao transacao) {
        if(!processadaComSucesso()){
            this.transacoes.add(transacao);
            atualizaStatusCompra(transacao);
            return true;
        }
        return false;
    }

    public void atualizaStatusCompra(Transacao transacao){
        if(transacao.comSucesso()){
            status = StatusCompra.SUCESSO;
        }else
            status = StatusCompra.FALHA;
    }

    public boolean processadaComSucesso(){
        return status.equals(StatusCompra.SUCESSO);
    }
}
