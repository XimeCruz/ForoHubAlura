package com.one_oracle.foro_alura.controller;

import com.one_oracle.foro_alura.model.Topico;
import com.one_oracle.foro_alura.model.request.TopicoRequest;
import com.one_oracle.foro_alura.service.TopicoService;
import jakarta.validation.Valid;
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
}
