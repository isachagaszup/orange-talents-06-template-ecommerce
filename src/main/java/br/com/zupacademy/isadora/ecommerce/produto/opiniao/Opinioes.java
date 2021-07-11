package br.com.zupacademy.isadora.ecommerce.produto.opiniao;

import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class Opinioes {

    private List<Opiniao> opinioes;

    public Opinioes(List<Opiniao> opinioes) {
        this.opinioes = opinioes;
    }

    public int totalNotas() {
        return opinioes.size();
    }

    public double media() {
        OptionalDouble average = opinioes.stream().mapToLong(opiniao -> opiniao.getNota()).average();
        return average.orElse(0.0);
    }

    public List<OpiniaoDoProdutoResponse> getOpinioes() {
        return opinioes.stream().map(OpiniaoDoProdutoResponse::new).collect(Collectors.toList());
    }
}
