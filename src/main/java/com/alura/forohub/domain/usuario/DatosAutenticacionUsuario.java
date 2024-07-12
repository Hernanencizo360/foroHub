package com.alura.forohub.domain.usuario;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;

public record DatosAutenticacionUsuario(@NotBlank String nombre,
                                        @JsonAlias("correoElectronico") @NotBlank String correElectronico,
                                        @NotBlank String contrasena) {
}
