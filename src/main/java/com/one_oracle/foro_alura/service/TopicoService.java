package com.one_oracle.foro_alura.service;

import com.one_oracle.foro_alura.model.Curso;
import com.one_oracle.foro_alura.model.Topico;
import com.one_oracle.foro_alura.model.Usuario;
import com.one_oracle.foro_alura.model.request.TopicoRequest;
import com.one_oracle.foro_alura.repository.CursoRepository;
import com.one_oracle.foro_alura.repository.TopicoRepository;
import com.one_oracle.foro_alura.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public Topico crearTopico(TopicoRequest topicoRequest) {

        // Verificar si el tópico ya existe
        if (topicoRepository.existsByTituloAndMensaje(topicoRequest.getTitulo(), topicoRequest.getMensaje())) {
            throw new RuntimeException("El tópico con el mismo título y mensaje ya existe.");
        }

        // Verificar que el autor y el curso existen
        Usuario autor = usuarioRepository.findById(topicoRequest.getAutorId())
                .orElseThrow(() -> new RuntimeException("El autor no existe."));

        Curso curso = cursoRepository.findById(topicoRequest.getCursoId())
                .orElseThrow(() -> new RuntimeException("El curso no existe."));

        Topico nuevoTopico = new Topico();
        nuevoTopico.setTitulo(topicoRequest.getTitulo());
        nuevoTopico.setMensaje(topicoRequest.getMensaje());
        nuevoTopico.setAutor(autor);
        nuevoTopico.setCurso(curso);
        nuevoTopico.setStatus("abierto");
        nuevoTopico.setFechaCreacion(LocalDateTime.now());

        return topicoRepository.save(nuevoTopico);
    }

    public Page<Topico> listarTopicos(Pageable pageable) {
        return topicoRepository.findAll(pageable);
    }

    public Page<Topico> listarTopicosPorCursoYAnio(String nombreCurso, int anio, Pageable pageable) {
        return topicoRepository.findByCursoNombreAndAnio(nombreCurso, anio, pageable);
    }

     public Optional<Topico> obtenerTopicoPorId(Long id) {
        return topicoRepository.findById(id);
    }
}
