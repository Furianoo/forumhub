package com.forumhub.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    @NotBlank
    private String login;

    @NotBlank
    private String senha;

    @NotBlank
    private String nome;
}
