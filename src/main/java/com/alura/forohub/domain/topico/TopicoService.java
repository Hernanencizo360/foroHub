package com.alura.forohub.domain.topico;

import com.alura.forohub.domain.curso.Curso;
import com.alura.forohub.domain.curso.CursoService;
import com.alura.forohub.domain.usuario.Usuario;
import com.alura.forohub.domain.usuario.UsuarioService;
import com.alura.forohub.infra.error.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CursoService cursoService;

    @Transactional
    public Long crearTopico(DatosRegistroTopico datos) {
        verificarDuplicado(datos);

        Usuario autor = usuarioService.obtenerUsuarioPorId(datos.autorId());
        Curso curso = cursoService.obtenerCursoPorId(datos.cursoId());

        Topico topico = new Topico();
        topico.setTitulo(datos.titulo());
        topico.setMensaje(datos.mensaje());
        topico.setAutor(autor);
        topico.setCurso(curso);
        topico.setStatus(EstadoTopico.ABIERTO);
        topico.setFechaCreacion(LocalDateTime.now());

        topicoRepository.save(topico);

        return topico.getId();
    }

    public DatosDetalleTopico convertirADatosDetalleTopico(Topico topico) {
        return new DatosDetalleTopico(
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getStatus(),
                topico.getFechaCreacion(),
                usuarioService.convertirADatosListarUsuario(topico.getAutor()),
                cursoService.convertirADatosListarCurso(topico.getCurso())
        );
    }

    private void verificarDuplicado(DatosRegistroTopico datos) {
        if (topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())) {
            throw new IllegalArgumentException("Tópico duplicado: ya existe un tópico con el mismo título y mensaje.");
        }
    }

    public Page<DatosDetalleTopico> listarTopicos(Pageable pageable) {
        return topicoRepository.findAll(pageable)
                .map(this::convertirADatosDetalleTopico);
    }

    public DatosDetalleTopico obtenerDetalleTopico(Long id) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tópico no encontrado con ID: " + id));
        return convertirADatosDetalleTopico(topico);
    }

    @Transactional
    public DatosDetalleTopico actualizarTopico(Long id, DatosActualizacionTopico datosActualizacion) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tópico no encontrado con ID: " + id));

        if (datosActualizacion.titulo() != null) {
            topico.setTitulo(datosActualizacion.titulo());
        }

        if (datosActualizacion.mensaje() != null) {
            topico.setMensaje(datosActualizacion.mensaje());
        }

        if (datosActualizacion.autorId() != null) {
            Usuario autor = usuarioService.obtenerUsuarioPorId(datosActualizacion.autorId());
            topico.setAutor(autor);
        }

        if (datosActualizacion.cursoId() != null) {
            Curso curso = cursoService.obtenerCursoPorId(datosActualizacion.cursoId());
            topico.setCurso(curso);
        }

        if (datosActualizacion.status() != null) {
            System.out.println("Estado recibido del dto: " + datosActualizacion.status());
            topico.setStatus(datosActualizacion.status());
        }

        Topico topicoActualizado = topicoRepository.save(topico);
        return convertirADatosDetalleTopico(topicoActualizado);
    }


    @Transactional
    public void eliminarTopico(Long id) {
        if (!topicoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Tópico no encontrado con ID: " + id);
        }
        topicoRepository.deleteById(id);
    }
}