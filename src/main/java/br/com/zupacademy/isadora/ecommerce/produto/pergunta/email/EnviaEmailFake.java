package br.com.zupacademy.isadora.ecommerce.produto.pergunta.email;

import br.com.zupacademy.isadora.ecommerce.produto.pergunta.Pergunta;
import br.com.zupacademy.isadora.ecommerce.produto.pergunta.email.Email;
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
}
