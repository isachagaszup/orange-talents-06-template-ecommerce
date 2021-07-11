package br.com.zupacademy.isadora.ecommerce.produto;

import br.com.zupacademy.isadora.ecommerce.produto.caracteristica.CaracteristicaProduto;
import br.com.zupacademy.isadora.ecommerce.produto.caracteristica.CaracteristicasDoProdutoResponse;
import br.com.zupacademy.isadora.ecommerce.produto.imagem.ImagemProduto;
import br.com.zupacademy.isadora.ecommerce.produto.opiniao.OpiniaoDoProdutoResponse;
import br.com.zupacademy.isadora.ecommerce.produto.opiniao.Opinioes;
import br.com.zupacademy.isadora.ecommerce.produto.pergunta.Pergunta;
import br.com.zupacademy.isadora.ecommerce.produto.pergunta.PerguntaDoProdutoResponse;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DetalhesDoProdutoResponse {

    private List<String> linksImagens;
    private String nome;
    private BigDecimal preco;
    private List<CaracteristicasDoProdutoResponse> caracteristicas;
    private String descricao;
    private List<OpiniaoDoProdutoResponse> opinioes;
    private Double mediaNota;
    private Integer totalNotas;
    private List<PerguntaDoProdutoResponse> perguntas;

    public DetalhesDoProdutoResponse(Produto produto) {
        this.linksImagens = linksResponse(produto.getImagens());
        this.nome = produto.getNome();
        this.preco = produto.getPreco();
        this.caracteristicas = caracteristicasResponse(produto.getCaracteristicas());
        this.descricao = produto.getDescricao();
        Opinioes opinioes = produto.getOpinioes();
        this.mediaNota = opinioes.media();
        this.totalNotas = opinioes.totalNotas();
        this.perguntas = perguntasResponse(produto.getPerguntas());

    }

    public static DetalhesDoProdutoResponse convert(Produto produto) {
        return new DetalhesDoProdutoResponse(produto);
    }

    private List<String> linksResponse(List<ImagemProduto> imagems){
        return imagems.stream().map(ImagemProduto::getLink).collect(Collectors.toList());
    }

    private List<CaracteristicasDoProdutoResponse> caracteristicasResponse(Set<CaracteristicaProduto> caracteristicas){
        return caracteristicas.stream().map(CaracteristicasDoProdutoResponse::new).collect(Collectors.toList());
    }

    private List<PerguntaDoProdutoResponse> perguntasResponse(List<Pergunta> perguntas){
        return perguntas.stream().map(PerguntaDoProdutoResponse::new).collect(Collectors.toList());
    }

    public List<String> getLinksImagens() {
        return linksImagens;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public List<CaracteristicasDoProdutoResponse> getCaracteristicas() {
        return caracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public List<OpiniaoDoProdutoResponse> getOpinioes() {
        return opinioes;
    }

    public List<PerguntaDoProdutoResponse> getPerguntas() {
        return perguntas;
    }

    public Double getMediaNota() { return mediaNota; }

    public Integer getTotalNotas() { return totalNotas; }
}
