package com.alura.forohub.domain.curso;

import com.alura.forohub.infra.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public DatosListarCurso convertirADatosListarCurso(Curso curso) {
        return new DatosListarCurso(curso.getNombre(), curso.getCategoria().name());
    }

    public Curso obtenerCursoPorId(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado con ID: " + id));
    }
}
