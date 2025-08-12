package br.com.forumhub.dto;

import java.time.LocalDateTime;

public class TopicoResponseDTO {

    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private String estado;
    private String autorNome;
    private String cursoNome;

    public TopicoResponseDTO(Long id, String titulo, String mensagem, LocalDateTime dataCriacao,
                            String estado, String autorNome, String cursoNome) {
        this.id = id;
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.dataCriacao = dataCriacao;
        this.estado = estado;
        this.autorNome = autorNome;
        this.cursoNome = cursoNome;
    }

    // Getters

    public Long getId() {
        return id;
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

    public String getEstado() {
        return estado;
    }

    public String getAutorNome() {
        return autorNome;
    }

    public String getCursoNome() {
        return cursoNome;
    }
}
    // Getters

    public Long getId() {
        return id;
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

    public String getEstado() {
        return estado;
    }

    public String getAutorNome() {
        return autorNome;
    }

    public String getCursoNome() {
        return cursoNome;
    }
}
