package com.alura.forohub.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DatosActualizacionUsuario(@NotBlank String nombre, @Email String correoElectronico, String contrasena) {}
