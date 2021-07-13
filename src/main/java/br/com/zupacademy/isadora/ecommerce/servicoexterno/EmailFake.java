package br.com.zupacademy.isadora.ecommerce.servicoexterno;

import org.springframework.stereotype.Component;

@Component
public class EmailFake implements Email {

    @Override
    public void send(String from, String to, String subject, String body) {
        System.out.println("\n---------------Enviando e-mail... -----------------");
        System.out.println("de: " + from);
        System.out.println("para: " + to);
        System.out.println("assunto: " + subject);
        System.out.println(body);
        System.out.println("------------------------------------------------------");
    }
}
