package br.com.zupacademy.isadora.ecommerce.autor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Locale;

public class UsuarioRequest {

    @NotBlank @Email
    private String login;
    @NotBlank @Size(min = 6, message = "senha deve conter no mínimo 6 caracteres")
    private String senha;

    public UsuarioRequest(@NotBlank String login, @NotBlank @Size(min = 6, message = "deve conter no mínimo que 6 caracteres") String senha) {
        this.login = login.toLowerCase(Locale.ROOT);
        this.senha = senha;
    }

    public Usuario toModel(){
        return new Usuario(login, new SenhaLimpa(senha));
    }


    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }
}
