package br.com.zupacademy.isadora.ecommerce.pedido.ranking;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/ranking")
public class RankingController {

    @PostMapping
    public ResponseEntity<String> registra(@Valid @RequestBody RankingRequest rankingRequest) throws InterruptedException {
        String mensagem = "\nVendedor " + rankingRequest.getIdVendedor() + " registrado no ranking de vendas.";
        System.out.println();
        System.out.println(mensagem);
        System.out.println();
        Thread.sleep(200);
        return ResponseEntity.ok(mensagem);
    }
}
