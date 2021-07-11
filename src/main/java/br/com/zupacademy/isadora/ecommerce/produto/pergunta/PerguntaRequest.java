package br.com.zupacademy.isadora.ecommerce.produto.pergunta;

import br.com.zupacademy.isadora.ecommerce.produto.Produto;
import br.com.zupacademy.isadora.ecommerce.usuario.Usuario;

import javax.validation.constraints.NotBlank;

public class PerguntaRequest {

    @NotBlank
    private String titulo;

    public PerguntaRequest() {
    }

    public PerguntaRequest(@NotBlank String titulo) {
        this.titulo = titulo;
    }

    public Pergunta toModel(Usuario usuario, Produto produto){
        return new Pergunta(titulo, usuario, produto);
    }

    public String getTitulo() {
        return titulo;
    }
}
