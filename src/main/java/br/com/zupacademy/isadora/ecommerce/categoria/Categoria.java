package br.com.zupacademy.isadora.ecommerce.categoria;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Categoria {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @ManyToOne
    private Categoria categoria;

    /**
     * hibernate only
     */
    @Deprecated
    public Categoria() {
    }

    public Categoria(@NotBlank String nome, Categoria categoria) {
        this.nome = nome;
        this.categoria = categoria;
    }
}
