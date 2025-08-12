package br.com.forumhub.service;

import br.com.forumhub.dto.TopicoRequestDTO;
import br.com.forumhub.dto.TopicoResponseDTO;
import br.com.forumhub.model.Curso;
import br.com.forumhub.model.Topico;
import br.com.forumhub.model.Usuario;
import br.com.forumhub.repository.CursoRepository;
import br.com.forumhub.repository.TopicoRepository;
import br.com.forumhub.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TopicoService {

    private final TopicoRepository topicoRepository;
    private final UsuarioRepository usuarioRepository;
    private final CursoRepository cursoRepository;

    public TopicoService(TopicoRepository topicoRepository,
                         UsuarioRepository usuarioRepository,
                         CursoRepository cursoRepository) {
        this.topicoRepository = topicoRepository;
        this.usuarioRepository = usuarioRepository;
        this.cursoRepository = cursoRepository;
    }

    public List<TopicoResponseDTO> listarTodos() {
        List<Topico> topicos = topicoRepository.findAll();
        return topicos.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<TopicoResponseDTO> buscarPorId(Long id) {
        return topicoRepository.findById(id).map(this::mapToResponseDTO);
    }

    public Optional<TopicoResponseDTO> criarTopico(TopicoRequestDTO dto) {
        Optional<Usuario> autor = usuarioRepository.findById(dto.getAutorId());
        Optional<Curso> curso = cursoRepository.findById(dto.getCursoId());

        if (autor.isEmpty() || curso.isEmpty()) {
            return Optional.empty();
        }

        Topico topico = new Topico();
        topico.setTitulo(dto.getTitulo());
        topico.setMensagem(dto.getMensagem());
        topico.setEstado(dto.getEstado());
        topico.setAutor(autor.get());
        topico.setCurso(curso.get());

        Topico salvo = topicoRepository.save(topico);
        return Optional.of(mapToResponseDTO(salvo));
    }

    public Optional<TopicoResponseDTO> atualizarTopico(Long id, TopicoRequestDTO dto) {
        Optional<Topico> topicoOpt = topicoRepository.findById(id);
        if (topicoOpt.isEmpty()) {
            return Optional.empty();
        }

        Optional<Usuario> autor = usuarioRepository.findById(dto.getAutorId());
        Optional<Curso> curso = cursoRepository.findById(dto.getCursoId());
        if (autor.isEmpty() || curso.isEmpty()) {
            return Optional.empty();
        }

        Topico topico = topicoOpt.get();
        topico.setTitulo(dto.getTitulo());
        topico.setMensagem(dto.getMensagem());
        topico.setEstado(dto.getEstado());
        topico.setAutor(autor.get());
        topico.setCurso(curso.get());

        Topico atualizado = topicoRepository.save(topico);
        return Optional.of(mapToResponseDTO(atualizado));
    }

    public boolean deletarTopico(Long id) {
        if (!topicoRepository.existsById(id)) {
            return false;
        }
        topicoRepository.deleteById(id);
        return true;
    }

    private TopicoResponseDTO mapToResponseDTO(Topico topico) {
        return new TopicoResponseDTO(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getDataCriacao(),
                topico.getEstado(),
                topico.getAutor().getNome(),
                topico.getCurso().getNome()
        );
    }
}
