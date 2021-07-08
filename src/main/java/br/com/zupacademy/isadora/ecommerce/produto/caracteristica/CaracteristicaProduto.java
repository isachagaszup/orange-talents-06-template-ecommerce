package br.com.zupacademy.isadora.ecommerce.produto.caracteristica;

import br.com.zupacademy.isadora.ecommerce.produto.Produto;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class CaracteristicaProduto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    @ManyToOne
    private Produto produto;

    /**
     * hibernate only
     */
    @Deprecated
    public CaracteristicaProduto() {
    }

    public CaracteristicaProduto(String nome, String descricao, Produto produto) {
        this.nome = nome;
        this.descricao = descricao;
        this.produto = produto;
    }

    public String getNome() {
        return this.nome;
    }

    public String getDescricao() {
        return this.descricao;
    }
}
