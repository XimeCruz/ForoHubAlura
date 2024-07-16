package com.one_oracle.foro_alura.model.request;


import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopicoRequest {

    @NotNull
    @NotEmpty
    private String titulo;

    @NotNull
    @NotEmpty
    private String mensaje;

    @NotNull
    private Long autorId;

    @NotNull
    private Long cursoId;

}

