package br.com.zupacademy.isadora.ecommerce.produto.opiniao;

import br.com.zupacademy.isadora.ecommerce.produto.Produto;
import br.com.zupacademy.isadora.ecommerce.usuario.Usuario;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Opiniao {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Min(value = 1) @Max(value = 5) @NotNull
    private Integer nota;
    @NotBlank
    private String titulo;
    @NotBlank @Size(max = 500)
    private String descricao;
    @ManyToOne
    private Produto produto;
    @ManyToOne
    private Usuario usuario;

    /**
     * hibernate only
     */
    @Deprecated
    public Opiniao() {
    }

    public Opiniao(@Min(value = 1) @Max(value = 5) @NotNull Integer nota, @NotBlank String titulo, @NotBlank @Size(max = 500) String descricao, Produto produto, Usuario usuario) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.produto = produto;
        this.usuario = usuario;
    }
}
