package br.com.zupacademy.isadora.ecommerce.autor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SenhaLimpa {

    @NotBlank @Size(min = 6)
    private String senha;

    public SenhaLimpa(@NotBlank @Size(min = 6) String senha) {
        this.senha = senha;
    }

    public String hash(){
        return new BCryptPasswordEncoder().encode(senha);
    }
}
