package br.com.zupacademy.isadora.ecommerce.produto;

import br.com.zupacademy.isadora.ecommerce.categoria.Categoria;
import br.com.zupacademy.isadora.ecommerce.produto.caracteristica.CaracteristicaProduto;
import br.com.zupacademy.isadora.ecommerce.produto.caracteristica.CaracteristicaProdutoRequest;
import br.com.zupacademy.isadora.ecommerce.produto.imagem.ImagemProduto;
import br.com.zupacademy.isadora.ecommerce.produto.opiniao.Opiniao;
import br.com.zupacademy.isadora.ecommerce.usuario.Usuario;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Produto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @NotNull
    private BigDecimal preco;
    private Integer quantidade;
    private String descricao;
    private LocalDateTime criadoEm = LocalDateTime.now();
    @OneToMany(mappedBy="produto", cascade= CascadeType.PERSIST)
    private Set<CaracteristicaProduto> caracteristicas = new HashSet<>();
    @ManyToOne
    private Categoria categoria;
    @ManyToOne
    private Usuario usuario;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "produto", cascade = CascadeType.MERGE)
    private List<ImagemProduto> imagens;
    @OneToMany(mappedBy = "produto")
    private List<Opiniao> opinioes;

    /**
     * hibernate only
     */
    @Deprecated
    public Produto() {
    }

    public Produto(@NotBlank String nome, @NotNull BigDecimal preco, Integer quantidade, String descricao, @Size(min = 3) Set<CaracteristicaProdutoRequest> caracteristicas, Categoria categoria, Usuario usuario) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.caracteristicas = caracteristicas.stream().map(c -> new CaracteristicaProduto(c.getNome(), c.getDescricao(), this))
                .collect(Collectors.toSet());;
        this.categoria = categoria;
        this.usuario = usuario;
    }

    public void addImagens(Set<String> links) {
        Set<ImagemProduto> collect = links.stream().map(link -> new ImagemProduto(this, link)).collect(Collectors.toSet());
        imagens.addAll(collect);
    }

    public boolean pertenceAo(Usuario usuarioLogado) {
        return usuario.equals(usuarioLogado);
    }

}
