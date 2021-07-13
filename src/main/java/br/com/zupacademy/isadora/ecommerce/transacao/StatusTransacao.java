package br.com.zupacademy.isadora.ecommerce.transacao;

public enum StatusTransacao {

    ERRO, SUCESSO;

    public static StatusTransacao get(int status){
        return StatusTransacao.values()[status];
    }
}
