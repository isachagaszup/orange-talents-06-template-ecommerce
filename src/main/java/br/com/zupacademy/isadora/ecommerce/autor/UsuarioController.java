package br.com.zupacademy.isadora.ecommerce.autor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    public void cadastra(@RequestBody @Valid UsuarioRequest usuarioRequest) {
        Usuario usuario = usuarioRequest.toModel();

        usuarioRepository.save(usuario);
    }
}
