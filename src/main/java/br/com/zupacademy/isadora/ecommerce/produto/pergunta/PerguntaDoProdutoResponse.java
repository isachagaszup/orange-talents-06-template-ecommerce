package br.com.zupacademy.isadora.ecommerce.produto.pergunta;

public class PerguntaDoProdutoResponse {

    private String titulo;

    public PerguntaDoProdutoResponse(Pergunta pergunta) {
        this.titulo = pergunta.getTitulo();
    }

    public String getTitulo() {
        return titulo;
    }
}
