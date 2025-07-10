package com.forumhub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RespostaRequestDTO {

    @NotBlank(message = "Mensagem é obrigatória")
    private String mensagem;

    @NotBlank(message = "Autor é obrigatório")
    private String autor;

    @NotNull(message = "ID do tópico é obrigatório")
    private Long topicoId;

    // Getters e setters
    public String getMensagem() {
        return mensagem;
    }
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public Long getTopicoId() {
        return topicoId;
    }
    public void setTopicoId(Long topicoId) {
        this.topicoId = topicoId;
    }
}
