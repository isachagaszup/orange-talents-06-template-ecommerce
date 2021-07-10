package br.com.zupacademy.isadora.ecommerce.produto.imagem;

import br.com.zupacademy.isadora.ecommerce.produto.Produto;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;

@Entity
public class ImagemProduto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Produto produto;
    @URL
    private String link;

    /**
     * hibernate only
     */
    @Deprecated
    public ImagemProduto() {
    }

    public ImagemProduto(Produto produto, @URL String link) {
        this.produto = produto;
        this.link = link;
    }
}
