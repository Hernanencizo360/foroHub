package com.alura.forohub.domain.curso;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroCurso(Long id, @NotBlank String nombre, @NotBlank @NotNull String categoria) {}
