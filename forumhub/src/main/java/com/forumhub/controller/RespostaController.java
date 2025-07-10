package com.forumhub.controller;

import com.forumhub.dto.RespostaRequestDTO;
import com.forumhub.dto.RespostaResponseDTO;
import com.forumhub.model.Resposta;
import com.forumhub.model.Topico;
import com.forumhub.service.RespostaService;
import com.forumhub.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/respostas")
public class RespostaController {

    private final RespostaService respostaService;
    private final TopicoService topicoService;

    public RespostaController(RespostaService respostaService, TopicoService topicoService) {
        this.respostaService = respostaService;
        this.topicoService = topicoService;
    }

    @GetMapping("/topico/{topicoId}")
    public ResponseEntity<List<RespostaResponseDTO>> listarPorTopico(@PathVariable Long topicoId) {
        if (topicoService.detalhar(topicoId) == null) {
            return ResponseEntity.notFound().build();
        }

        List<Resposta> respostas = respostaService.listarPorTopico(topicoId);
        List<RespostaResponseDTO> dtoList = respostas.stream()
                .map(r -> new RespostaResponseDTO(
                        r.getId(),
                        r.getMensagem(),
                        r.getDataCriacao(),
                        r.getAutor(),
                        r.getTopico().getId()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtoList);
    }

    @PostMapping
    public ResponseEntity<RespostaResponseDTO> cadastrar(@RequestBody @Valid RespostaRequestDTO dto) {
        var topicoDetalhe = topicoService.detalhar(dto.getTopicoId());
        if (topicoDetalhe == null) {
            return ResponseEntity.badRequest().build();
        }

        Resposta resposta = new Resposta();
        resposta.setMensagem(dto.getMensagem());
        resposta.setAutor(dto.getAutor());

        Topico topico = new Topico();
        topico.setId(dto.getTopicoId());
        resposta.setTopico(topico);

        Resposta respostaSalva = respostaService.cadastrar(resposta);

        RespostaResponseDTO respostaResponseDTO = new RespostaResponseDTO(
                respostaSalva.getId(),
                respostaSalva.getMensagem(),
                respostaSalva.getDataCriacao(),
                respostaSalva.getAutor(),
                respostaSalva.getTopico().getId()
        );

        URI uri = URI.create("/respostas/" + respostaSalva.getId());
        return ResponseEntity.created(uri).body(respostaResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespostaResponseDTO> detalhar(@PathVariable Long id) {
        return respostaService.buscarPorId(id)
                .map(r -> new RespostaResponseDTO(
                        r.getId(),
                        r.getMensagem(),
                        r.getDataCriacao(),
                        r.getAutor(),
                        r.getTopico().getId()
                ))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<RespostaResponseDTO> atualizar(@PathVariable Long id,
                                                         @RequestBody @Valid RespostaRequestDTO dto) {
        // Verifica se o tópico existe
        var topicoDetalhe = topicoService.detalhar(dto.getTopicoId());
        if (topicoDetalhe == null) {
            return ResponseEntity.badRequest().body(null);
        }

        try {
            Resposta respostaAtualizada = respostaService.atualizar(id, dto);

            // Atualiza tópico também caso queira, mas se não altera, pode remover esta linha
            Topico topico = new Topico();
            topico.setId(dto.getTopicoId());
            respostaAtualizada.setTopico(topico);

            RespostaResponseDTO responseDTO = new RespostaResponseDTO(
                    respostaAtualizada.getId(),
                    respostaAtualizada.getMensagem(),
                    respostaAtualizada.getDataCriacao(),
                    respostaAtualizada.getAutor(),
                    respostaAtualizada.getTopico().getId()
            );
            return ResponseEntity.ok(responseDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (respostaService.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        respostaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
