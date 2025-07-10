package com.forumhub.service;

import com.forumhub.dto.RespostaRequestDTO;
import com.forumhub.model.Resposta;
import com.forumhub.repository.RespostaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RespostaService {

    private final RespostaRepository respostaRepository;

    public RespostaService(RespostaRepository respostaRepository) {
        this.respostaRepository = respostaRepository;
    }

    public List<Resposta> listarPorTopico(Long topicoId) {
        return respostaRepository.findByTopicoId(topicoId);
    }

    public Optional<Resposta> buscarPorId(Long id) {
        return respostaRepository.findById(id);
    }

    @Transactional
    public Resposta cadastrar(Resposta resposta) {
        return respostaRepository.save(resposta);
    }

    @Transactional
    public Resposta atualizar(Long id, RespostaRequestDTO dto) {
        Resposta resposta = respostaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Resposta não encontrada"));

        resposta.setMensagem(dto.getMensagem());
        resposta.setAutor(dto.getAutor());
        // Se quiser alterar tópico, pode incluir aqui, mas geralmente não se altera.

        return respostaRepository.save(resposta);
    }

    @Transactional
    public void deletar(Long id) {
        respostaRepository.deleteById(id);
    }
}
