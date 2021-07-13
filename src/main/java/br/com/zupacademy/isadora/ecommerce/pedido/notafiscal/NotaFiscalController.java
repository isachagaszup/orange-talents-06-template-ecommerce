package br.com.zupacademy.isadora.ecommerce.pedido.notafiscal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/nota-fiscal")
public class NotaFiscalController {

    @PostMapping
    public ResponseEntity<String> criaNota(@Valid @RequestBody NotaFiscalRequest notaFiscalRequest) throws InterruptedException {
        String nota = "\n==================== Nota Fiscal aqui ====================" +
                "\nidCompra: " + notaFiscalRequest.getIdCompra() +
                "\nidUsuario: " + notaFiscalRequest.getIdUsuario() +
                "\n==========================================================";
        System.out.println(nota);
        Thread.sleep(200);
        return ResponseEntity.ok(nota);
    }
}
