package com.one_oracle.foro_alura.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    private String nombre;

    @NotNull
    @NotEmpty
    @Email
    @Column(unique = true)
    private String correoElectronico;

    @NotNull
    @NotEmpty
    private String contrasena;

    @ManyToMany
    private List<Perfil> perfiles;

}

