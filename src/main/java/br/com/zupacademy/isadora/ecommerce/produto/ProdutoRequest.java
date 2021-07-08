package br.com.zupacademy.isadora.ecommerce.produto;

import br.com.zupacademy.isadora.ecommerce.categoria.Categoria;
import br.com.zupacademy.isadora.ecommerce.categoria.CategoriaRepository;
import br.com.zupacademy.isadora.ecommerce.produto.caracteristica.CaracteristicaProdutoRequest;
import br.com.zupacademy.isadora.ecommerce.usuario.Usuario;
import br.com.zupacademy.isadora.ecommerce.validador.anotacao.ExistsId;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class ProdutoRequest {

    @NotBlank
    private String nome;
    @NotNull
    private BigDecimal preco;
    @NotNull @Min(value = 0) @PositiveOrZero
    private Integer quantidade;
    @NotBlank @Length(max = 1000)
    private String descricao;
    @Size(min = 3, message = "Precisa ter 3 ou mais caracteristicas.")
    private Set<CaracteristicaProdutoRequest> caracteristicas = new HashSet<>();
    @NotNull @ExistsId(domainClass = Categoria.class, fieldName = "id")
    private Long categoriaId;


    public ProdutoRequest(@NotBlank String nome, @NotNull BigDecimal preco, @NotNull @Min(value = 0) @PositiveOrZero Integer quantidade, @NotBlank @Length(max = 1000) String descricao, @Size(min = 3) Set<CaracteristicaProdutoRequest> caracteristicas, @NotNull Long categoriaId) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.caracteristicas = caracteristicas;
        this.categoriaId = categoriaId;
    }

    public Produto toModel(CategoriaRepository categoriaRepository, Long usuarioId) {
        Categoria categoria = categoriaRepository.getById(categoriaId);
        Usuario usuario = new Usuario(usuarioId);

        return new Produto(nome, preco, quantidade, descricao, caracteristicas, categoria, usuario);
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public Set<CaracteristicaProdutoRequest> getCaracteristicas() {
        return caracteristicas;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

}
