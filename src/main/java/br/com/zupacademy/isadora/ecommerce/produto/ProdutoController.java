package br.com.zupacademy.isadora.ecommerce.produto;

import br.com.zupacademy.isadora.ecommerce.categoria.CategoriaRepository;
import br.com.zupacademy.isadora.ecommerce.produto.imagem.ImagemRequest;
import br.com.zupacademy.isadora.ecommerce.produto.imagem.upload.Uploader;
import br.com.zupacademy.isadora.ecommerce.usuario.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private CategoriaRepository categoriaRepository;
    private ProdutoRepository produtoRepository;
    private Uploader uploader;

    public ProdutoController(CategoriaRepository categoriaRepository, ProdutoRepository produtoRepository, Uploader uploader) {
        this.categoriaRepository = categoriaRepository;
        this.produtoRepository = produtoRepository;
        this.uploader = uploader;
    }

    @PostMapping
    public void cadastra(@RequestBody @Valid ProdutoRequest produtoRequest, @AuthenticationPrincipal Usuario usuario) {
        Produto produto = produtoRequest.toModel(categoriaRepository, usuario.getId());

        produtoRepository.save(produto);
    }

    @PostMapping("/{id}/imagens")
    public void cadastraImagem(@PathVariable Long id, @Valid ImagemRequest imagemRequest, @AuthenticationPrincipal Usuario usuario) {
        Optional<Produto> optional = produtoRepository.findById(id);

        if (optional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id do produto inválido!");
        }

        Produto produto = optional.get();

        if (!produto.pertenceAo(usuario)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Esse produto não pertence ao seu usuário!");
        }

        Set<String> links = uploader.envia(imagemRequest.getImagens());
        produto.addImagens(links);

        produto = produtoRepository.save(produto);
    }
}
