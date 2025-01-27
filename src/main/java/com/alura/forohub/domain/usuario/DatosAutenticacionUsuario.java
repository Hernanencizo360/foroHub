package com.alura.forohub.domain.usuario;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;

public record DatosAutenticacionUsuario(@JsonAlias("correoElectronico") @NotBlank String correElectronico, @NotBlank String contrasena) {}
