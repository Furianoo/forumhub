package com.forumhub.controller;

import com.forumhub.dto.LoginDTO;
import com.forumhub.dto.TokenResponseDTO;
import com.forumhub.model.Usuario;
import com.forumhub.service.TokenService;
import com.forumhub.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UsuarioService usuarioService;

    public AuthController(AuthenticationManager authenticationManager,
                          TokenService tokenService,
                          UsuarioService usuarioService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<TokenResponseDTO> autenticar(@RequestBody @Valid LoginDTO loginDTO) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDTO.getLogin(), loginDTO.getSenha());

        try {
            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            Usuario usuario = usuarioService.buscarPorLogin(loginDTO.getLogin())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

            String token = tokenService.gerarToken(usuario);

            return ResponseEntity.ok(new TokenResponseDTO(token));

        } catch (AuthenticationException ex) {
            // Pode personalizar a resposta, aqui retorno 401 Unauthorized
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
