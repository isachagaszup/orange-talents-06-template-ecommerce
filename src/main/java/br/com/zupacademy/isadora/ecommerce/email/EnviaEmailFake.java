package br.com.zupacademy.isadora.ecommerce.email;

import br.com.zupacademy.isadora.ecommerce.pedido.Pedido;
import br.com.zupacademy.isadora.ecommerce.produto.pergunta.Pergunta;
import org.springframework.stereotype.Component;

@Component
public class EnviaEmailFake implements Email {

    @Override
    public void envia(Pergunta pergunta) {
        String texto = pergunta.getTitulo();
        String usuario = pergunta.getUsuario().getUsername();
        String produto = pergunta.getProduto().getNome();

        System.out.println("Nova pergunta de: " + usuario);
        System.out.println("Pergunta: " + texto);
        System.out.println("Produto: " + produto);
    }

    @Override
    public void enviaPedido(Pedido pedido) {
        String usuario = pedido.getComprador().getUsername();
        String produto = pedido.getProduto().getNome();

        System.out.println("Você tem uma nova compra de : " + usuario);
        System.out.println("Produto: " + produto + " Quantidade: " + pedido.getQuantidade());
    }
}
