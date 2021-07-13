package br.com.zupacademy.isadora.ecommerce.pedido.notafiscal;

import br.com.zupacademy.isadora.ecommerce.pedido.EventoCompraSucesso;
import br.com.zupacademy.isadora.ecommerce.pedido.Pedido;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class NotaFiscal implements EventoCompraSucesso {

    private String URL_RANKING = "http://localhost:8080/nota-fiscal" ;

    @Override
    public void process(Pedido pedido) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity(URL_RANKING, new NotaFiscalRequest(pedido.getId(), pedido.getComprador().getId()), String.class);

    }
}
