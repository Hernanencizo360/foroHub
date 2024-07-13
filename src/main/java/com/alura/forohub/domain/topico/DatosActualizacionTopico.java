package com.alura.forohub.domain.topico;

import jakarta.validation.constraints.NotBlank;

public record DatosActualizacionTopico(
        @NotBlank String titulo,
        @NotBlank String mensaje,
        EstadoTopico status,
        Long autorId,
        Long cursoId
) {}

