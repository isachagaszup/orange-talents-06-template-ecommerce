package br.com.zupacademy.isadora.ecommerce.pedido.ranking;

import br.com.zupacademy.isadora.ecommerce.pedido.EventoCompraSucesso;
import br.com.zupacademy.isadora.ecommerce.pedido.Pedido;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Set;

@Component
public class Ranking implements EventoCompraSucesso {

    private String URL_RANKING = "http://localhost:8080/ranking";

    @Override
    public void process(Pedido pedido) {
        Set<Long> idsVendedores = Collections.singleton(pedido.getProduto().getUsuario().getId());
        idsVendedores.forEach(idVendedor -> {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.postForEntity(URL_RANKING, new RankingRequest(pedido.getId(), idVendedor), String.class);
        });
    }
}
