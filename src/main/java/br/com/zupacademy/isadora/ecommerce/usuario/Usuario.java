package br.com.zupacademy.isadora.ecommerce.usuario;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
public class Usuario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @NotBlank @Email
    private String login;
    @Column(nullable = false)
    @NotBlank @Size(min = 6)
    private String senha;
    @NotNull
    private LocalDateTime criadoEm = LocalDateTime.now();

    /*
    hibernate only
     */
    @Deprecated
    public Usuario() {
    }

    public Usuario(@NotBlank @Email String login, SenhaLimpa senhaLimpa) {
        this.login = login;
        this.senha = senhaLimpa.hash();
    }
}
