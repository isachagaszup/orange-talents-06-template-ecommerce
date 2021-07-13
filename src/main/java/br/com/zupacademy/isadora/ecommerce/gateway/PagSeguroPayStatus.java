package br.com.zupacademy.isadora.ecommerce.gateway;

import br.com.zupacademy.isadora.ecommerce.transacao.StatusTransacao;

public enum PagSeguroPayStatus {

    ERRO, SUCESSO;

    public StatusTransacao convert() {
        if (this.equals(PagSeguroPayStatus.SUCESSO)){
            return StatusTransacao.SUCESSO;
        }else{
            return StatusTransacao.ERRO;
        }
    }

}
