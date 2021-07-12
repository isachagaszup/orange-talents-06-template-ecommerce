package br.com.zupacademy.isadora.ecommerce.email;


import br.com.zupacademy.isadora.ecommerce.pedido.Pedido;
import br.com.zupacademy.isadora.ecommerce.produto.pergunta.Pergunta;

public interface Email {

    void envia(Pergunta pergunta);
    void enviaPedido(Pedido pedido);
}
