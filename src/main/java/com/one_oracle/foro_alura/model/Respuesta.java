package com.one_oracle.foro_alura.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    @ManyToOne
    private Usuario autor;
    @ManyToOne
    private Topico topico;
    private boolean solucion;
}
