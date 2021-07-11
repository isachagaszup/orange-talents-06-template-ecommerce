package br.com.zupacademy.isadora.ecommerce.produto.pergunta;

import br.com.zupacademy.isadora.ecommerce.produto.Produto;
import br.com.zupacademy.isadora.ecommerce.usuario.Usuario;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

@Entity
public class Pergunta {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String titulo;
    @NotNull @PastOrPresent
    private LocalDateTime criadoEm = LocalDateTime.now();
    @ManyToOne
    private Usuario usuario;
    @ManyToOne
    private Produto produto;

    /**
     * hibernate only
     */
    @Deprecated
    public Pergunta() {
    }

    public Pergunta(@NotBlank String titulo, Usuario usuario, Produto produto) {
        this.titulo = titulo;
        this.usuario = usuario;
        this.produto = produto;
    }

    public String getTitulo() {
        return titulo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Produto getProduto() {
        return produto;
    }
}
