package com.forumhub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.context.annotation.Role;

public record DadosCadastroUsuario(
        @NotBlank String nome,
        @NotBlank String login,
        @NotBlank String senha,
        @NotNull Role role
) {}