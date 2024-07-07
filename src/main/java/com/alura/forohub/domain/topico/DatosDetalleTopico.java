package com.alura.forohub.domain.topico;

import com.alura.forohub.domain.curso.DatosListarCurso;
import com.alura.forohub.domain.curso.DatosRegistroCurso;
import com.alura.forohub.domain.usuario.DatosListarUsuario;

import java.time.LocalDateTime;

public record DatosDetalleTopico(
        String titulo,
        String mensaje,
        EstadoTopico status,
        LocalDateTime fechaCreacion,
        DatosListarUsuario autor,
        DatosListarCurso curso
) {};


