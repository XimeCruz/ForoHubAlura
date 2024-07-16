package com.one_oracle.foro_alura.controller;

import com.one_oracle.foro_alura.model.Topico;
import com.one_oracle.foro_alura.model.request.TopicoRequest;
import com.one_oracle.foro_alura.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    public ResponseEntity<?> crearTopico(@RequestBody @Valid TopicoRequest topicoRequest) {
        try {
            Topico topicoGuardado = topicoService.crearTopico(topicoRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(topicoGuardado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Page<Topico>> listarTopicos(
            @PageableDefault(sort = "fechaCreacion", direction = Sort.Direction.ASC, size = 10) Pageable pageable) {
        Page<Topico> topicos = topicoService.listarTopicos(pageable);
        return ResponseEntity.ok(topicos);
    }

    @GetMapping("/buscar")
    public ResponseEntity<Page<Topico>> buscarTopicos(
            @RequestParam String nombreCurso,
            @RequestParam int anio,
            @PageableDefault(sort = "fechaCreacion", direction = Sort.Direction.ASC, size = 10) Pageable pageable) {
        Page<Topico> topicos = topicoService.listarTopicosPorCursoYAnio(nombreCurso, anio, pageable);
        return ResponseEntity.ok(topicos);
    }
}
