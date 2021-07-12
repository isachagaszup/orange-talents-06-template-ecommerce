package br.com.zupacademy.isadora.ecommerce.produto.pergunta;

import br.com.zupacademy.isadora.ecommerce.produto.Produto;
import br.com.zupacademy.isadora.ecommerce.produto.ProdutoRepository;
import br.com.zupacademy.isadora.ecommerce.email.Email;
import br.com.zupacademy.isadora.ecommerce.usuario.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class PerguntaController {

    private ProdutoRepository produtoRepository;
    private PerguntaRepository perguntaRepository;
    private Email email;

    public PerguntaController(ProdutoRepository produtoRepository, PerguntaRepository perguntaRepository, Email email) {
        this.produtoRepository = produtoRepository;
        this.perguntaRepository = perguntaRepository;
        this.email = email;
    }

    @PostMapping("/produtos/{id}/perguntas")
    public void criaPergunta(@PathVariable Long id, @RequestBody @Valid PerguntaRequest perguntaRequest, @AuthenticationPrincipal Usuario usuario) {
        Optional <Produto> optional = produtoRepository.findById(id);

        if (optional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id do produto inválido!");
        }

        Produto produto = optional.get();

        Pergunta pergunta = perguntaRequest.toModel(usuario, produto);
        perguntaRepository.save(pergunta);

        email.envia(pergunta);
    }
}
