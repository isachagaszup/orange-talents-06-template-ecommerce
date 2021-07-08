package br.com.zupacademy.isadora.ecommerce.produto.caracteristica;

import br.com.zupacademy.isadora.ecommerce.produto.Produto;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class CaracteristicaProdutoRequest {

    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;

    public CaracteristicaProdutoRequest() {
    }

    public CaracteristicaProdutoRequest(@NotBlank String nome, @NotBlank String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public CaracteristicaProduto toModel(Produto produto){
        return new CaracteristicaProduto(nome, descricao, produto);
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CaracteristicaProdutoRequest that = (CaracteristicaProdutoRequest) o;
        return Objects.equals(nome, that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}
