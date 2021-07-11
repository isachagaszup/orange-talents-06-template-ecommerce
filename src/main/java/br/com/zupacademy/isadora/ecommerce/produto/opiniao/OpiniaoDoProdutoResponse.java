package br.com.zupacademy.isadora.ecommerce.produto.opiniao;

public class OpiniaoDoProdutoResponse {

    private Integer nota;
    private String titulo;
    private String descricao;

    public OpiniaoDoProdutoResponse(Opiniao opiniao) {
        this.nota = opiniao.getNota();
        this.titulo = opiniao.getTitulo();
        this.descricao = opiniao.getDescricao();
    }

    public Integer getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }
}
