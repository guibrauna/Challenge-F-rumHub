package br.com.forumhub.controller;

import br.com.forumhub.dto.TopicoRequestDTO;
import br.com.forumhub.dto.TopicoResponseDTO;
import br.com.forumhub.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    private final TopicoService topicoService;

    public TopicoController(TopicoService topicoService) {
        this.topicoService = topicoService;
    }

    @GetMapping
    public List<TopicoResponseDTO> listar() {
        return topicoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoResponseDTO> buscarPorId(@PathVariable Long id) {
        Optional<TopicoResponseDTO> topico = topicoService.buscarPorId(id);
        if (topico.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(topico.get());
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TopicoResponseDTO> criar(@Valid @RequestBody TopicoRequestDTO dto) {
        Optional<TopicoResponseDTO> topicoCriado = topicoService.criarTopico(dto);
        if (topicoCriado.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(topicoCriado.get());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TopicoResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody TopicoRequestDTO dto) {
        Optional<TopicoResponseDTO> topicoAtualizado = topicoService.atualizarTopico(id, dto);
        if (topicoAtualizado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(topicoAtualizado.get());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        boolean deletado = topicoService.deletarTopico(id);
        if (!deletado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
