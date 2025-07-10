package com.forumhub.dto;

import java.time.LocalDateTime;

public class RespostaResponseDTO {

    private Long id;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private String autor;
    private Long topicoId;

    public RespostaResponseDTO(Long id, String mensagem, LocalDateTime dataCriacao, String autor, Long topicoId) {
        this.id = id;
        this.mensagem = mensagem;
        this.dataCriacao = dataCriacao;
        this.autor = autor;
        this.topicoId = topicoId;
    }

    // Getters (somente leitura, por segurança)
    public Long getId() {
        return id;
    }
    public String getMensagem() {
        return mensagem;
    }
    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
    public String getAutor() {
        return autor;
    }
    public Long getTopicoId() {
        return topicoId;
    }
}
