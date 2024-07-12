package com.alura.forohub.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record DatosActualizacionUsuario(@NotBlank String nombre,
                                        @NotBlank String correoElectronico,
                                        @NotBlank String contrasena
) {}
