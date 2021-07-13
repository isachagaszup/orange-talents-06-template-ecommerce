package br.com.zupacademy.isadora.ecommerce.servicoexterno;

import br.com.zupacademy.isadora.ecommerce.pedido.EventoCompraSucesso;
import br.com.zupacademy.isadora.ecommerce.pedido.Pedido;
import br.com.zupacademy.isadora.ecommerce.produto.pergunta.Pergunta;
import org.springframework.stereotype.Component;


@Component
public class SenderEmail implements EventoCompraSucesso {

    private final EmailFake emailFake;

    public SenderEmail(EmailFake emailFake) {
        this.emailFake = emailFake;
    }

    public void emailPergunta(Pergunta pergunta){
        emailFake.send(pergunta.getUsuario().getUsername(),
                pergunta.getProduto().getUsuario().getUsername(),
                "Nova pergunta do produto: " + pergunta.getProduto().getNome(),
                pergunta.getTitulo());
    }

    public void emailPagamentoFalhou(Pedido pedido, String uriRedirecionamento){
        emailFake.send(
                "mercadolivre@gmail.com",
                pedido.getComprador().getUsername(),
                "Infelizmente o pagamento da sua compra foi negado :C",
                "Tentar efetuar a compra novamente acessando o link: " + uriRedirecionamento);
    }

    @Override
    public void process(Pedido pedido) {
        String produto = pedido.getProduto().getNome();
        emailFake.send(
                "mercadolivre@gmail.com",
                pedido.getComprador().getUsername(),
                "O pagamento da sua compra foi aprovado o/ ",
                "O Pagamento do produto " + produto + " foi bem sucedido, em breve enviaremos seu produto!!");
    }
}