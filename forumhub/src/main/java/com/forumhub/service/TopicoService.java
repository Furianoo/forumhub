package com.forumhub.service;

import com.forumhub.dto.TopicoDTO;
import com.forumhub.model.Topico;
import com.forumhub.repository.TopicoRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TopicoService {

    private final TopicoRepository topicoRepository;

    public TopicoService(TopicoRepository topicoRepository) {
        this.topicoRepository = topicoRepository;
    }

    public Page<Topico> listar(String curso, Pageable pageable) {
        if (curso != null && !curso.isBlank()) {
            return topicoRepository.findByCursoContainingIgnoreCase(curso, pageable);
        }
        return topicoRepository.findAll(pageable);
    }

    public Optional<Topico> buscarPorId(Long id) {
        return topicoRepository.findById(id);
    }

    @Transactional
    public Topico cadastrar(TopicoDTO dto) {
        if (topicoRepository.findByTituloAndMensagem(dto.getTitulo(), dto.getMensagem()).isPresent()) {
            throw new IllegalArgumentException("Tópico duplicado");
        }

        Topico topico = new Topico();
        topico.setTitulo(dto.getTitulo());
        topico.setMensagem(dto.getMensagem());
        topico.setStatus(dto.getStatus());
        topico.setAutor(dto.getAutor());
        topico.setCurso(dto.getCurso());

        return topicoRepository.save(topico);
    }

    @Transactional
    public Topico atualizar(Long id, TopicoDTO dto) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tópico não encontrado"));

        topico.setTitulo(dto.getTitulo());
        topico.setMensagem(dto.getMensagem());
        topico.setStatus(dto.getStatus());
        topico.setAutor(dto.getAutor());
        topico.setCurso(dto.getCurso());

        return topicoRepository.save(topico);
    }

    @Transactional
    public void deletar(Long id) {
        if (!topicoRepository.existsById(id)) {
            throw new IllegalArgumentException("Tópico não encontrado");
        }
        topicoRepository.deleteById(id);
    }

    public Topico detalhar(Long topicoId) {
        return topicoRepository.findById(topicoId).orElse(null);
    }
}
