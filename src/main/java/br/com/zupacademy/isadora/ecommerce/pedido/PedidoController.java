package br.com.zupacademy.isadora.ecommerce.pedido;

import br.com.zupacademy.isadora.ecommerce.email.Email;
import br.com.zupacademy.isadora.ecommerce.produto.ProdutoRepository;
import br.com.zupacademy.isadora.ecommerce.usuario.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private ProdutoRepository produtoRepository;
    private Email email;
    private PedidoRepository pedidoRepository;

    public PedidoController(ProdutoRepository produtoRepository, Email email, PedidoRepository pedidoRepository) {
        this.produtoRepository = produtoRepository;
        this.email = email;
        this.pedidoRepository = pedidoRepository;
    }

    @PostMapping
    public ResponseEntity<String> cadastraPedido(UriComponentsBuilder uriComponentsBuilder, @RequestBody @Valid PedidoRequest pedidoRequest, @AuthenticationPrincipal Usuario usuario) {
        Pedido pedido = pedidoRequest.toModel(usuario, produtoRepository);

        String uri = pedido.getRedirectUrl(uriComponentsBuilder);
        pedidoRepository.save(pedido);
        produtoRepository.save(pedido.getProduto());

        email.enviaPedido(pedido);

        return ResponseEntity.status(302).body(uri);
    }
}
