package com.forumhub.dto;

import com.forumhub.model.StatusTopico;

import java.time.LocalDateTime;

public class TopicoDetalheDTO {

    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private StatusTopico status;
    private String autor;
    private String curso;

    public TopicoDetalheDTO(String titulo, String mensagem, LocalDateTime dataCriacao,
                            StatusTopico status, String autor, String curso) {
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.dataCriacao = dataCriacao;
        this.status = status;
        this.autor = autor;
        this.curso = curso;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public StatusTopico getStatus() {
        return status;
    }

    public String getAutor() {
        return autor;
    }

    public String getCurso() {
        return curso;
    }
}
