package com.forumhub.controller;

import com.forumhub.dto.UsuarioDTO;
import com.forumhub.model.Usuario;
import com.forumhub.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<Usuario> cadastrar(@RequestBody @Valid UsuarioDTO dto) {
        if (usuarioService.existsByLogin(dto.getLogin())) {
            return ResponseEntity.badRequest().build();
        }

        Usuario usuario = Usuario.builder()
                .login(dto.getLogin())
                .senha(dto.getSenha()) // será encriptada no service
                .nome(dto.getNome())
                .build();

        Usuario salvo = usuarioService.salvarUsuario(usuario);

        return ResponseEntity.created(URI.create("/usuarios/" + salvo.getLogin())).body(salvo);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarTodos() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @GetMapping("/{login}")
    public ResponseEntity<Usuario> buscarPorLogin(@PathVariable String login) {
        return usuarioService.buscarPorLogin(login)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{login}")
    public ResponseEntity<Void> deletarPorLogin(@PathVariable String login) {
        var usuarioOpt = usuarioService.buscarPorLogin(login);
        if (usuarioOpt.isPresent()) {
            usuarioService.deletarUsuario(usuarioOpt.get().getId());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
