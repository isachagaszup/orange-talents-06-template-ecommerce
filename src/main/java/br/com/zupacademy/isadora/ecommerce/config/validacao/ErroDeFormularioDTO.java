package br.com.zupacademy.isadora.ecommerce.config.validacao;

public class ErroDeFormularioDTO {
    private String campo;
    private String erro;


    public ErroDeFormularioDTO(String campo, String erro) {
        this.campo = campo;
        this.erro = erro;
    }

    public String getCampo() {
        return campo;
    }

    public String getErro() {
        return erro;
    }
}
