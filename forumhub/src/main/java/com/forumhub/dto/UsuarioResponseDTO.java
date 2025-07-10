package com.forumhub.dto;

public class UsuarioResponseDTO {
    private String login;
    private String nome;

    public UsuarioResponseDTO(String login, String nome) {
        this.login = login;
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public String getNome() {
        return nome;
    }
}
