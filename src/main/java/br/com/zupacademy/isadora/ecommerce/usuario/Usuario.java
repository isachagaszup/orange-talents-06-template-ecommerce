package br.com.zupacademy.isadora.ecommerce.usuario;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
public class Usuario implements UserDetails {

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

    public Usuario(Long id) {
        this.id = id;
    }

    public Usuario(@NotBlank @Email String login, SenhaLimpa senhaLimpa) {
        this.login = login;
        this.senha = senhaLimpa.hash();
    }

    public Long getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
