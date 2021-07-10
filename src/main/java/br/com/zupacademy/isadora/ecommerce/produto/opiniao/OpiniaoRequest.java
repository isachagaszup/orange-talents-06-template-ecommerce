package br.com.zupacademy.isadora.ecommerce.produto.opiniao;

import br.com.zupacademy.isadora.ecommerce.produto.Produto;
import br.com.zupacademy.isadora.ecommerce.usuario.Usuario;

import javax.validation.constraints.*;

public class OpiniaoRequest {

    @Min(value = 1) @Max(value = 5) @NotNull
    private Integer nota;
    @NotBlank
    private String titulo;
    @NotBlank @Size(max = 500)
    private String descricao;

    public OpiniaoRequest(@Min(value = 1) @Max(value = 5) @NotNull Integer nota, @NotBlank String titulo, @NotBlank @Size(max = 500) String descricao) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public Opiniao toModel(@NotNull Produto produto, @NotNull Usuario usuario){
        return new Opiniao(nota, titulo, descricao, produto, usuario);
    }
}
