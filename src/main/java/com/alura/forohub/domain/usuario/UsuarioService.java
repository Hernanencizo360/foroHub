package com.alura.forohub.domain.usuario;

import com.alura.forohub.domain.perfil.Perfil;
import com.alura.forohub.domain.perfil.PerfilService;
import com.alura.forohub.infra.error.EmailAlreadyExistsException;
import com.alura.forohub.infra.error.ResourceNotFoundException;
import com.alura.forohub.infra.error.UsuarioNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PerfilService perfilService;

    public DatosDetalleUsuario convertirADatosListarUsuario(Usuario usuario) {
        return new DatosDetalleUsuario(usuario.getNombre(), usuario.getCorreoElectronico());
    }

    public Usuario obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id));
    }

    public Long crearUsuario(DatosRegistroUsuario dto) {
        if (usuarioRepository.existsByCorreoElectronico(dto.correoElectronico())) {
            throw new EmailAlreadyExistsException("El correo electrónico ya está registrado en la BBDD.");
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(dto.nombre());
        usuario.setCorreoElectronico(dto.correoElectronico());
        usuario.setContrasena(passwordEncoder.encode(dto.contrasena()));

        usuario.getPerfiles().add(perfilService.obtenerPerfilUsuario());
        usuarioRepository.save(usuario);
        return usuario.getId();
    }

    @Transactional
    public DatosDetalleUsuario actualizarUsuario(Long id, DatosActualizacionUsuario dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id));
        usuario.setNombre(dto.nombre());

        // Actualizar solo si el campo está presente en el DTO
        if (dto.correoElectronico() != null && !dto.correoElectronico().isEmpty()) {
            usuario.setCorreoElectronico(dto.correoElectronico());
        }

        if (dto.contrasena() != null && !dto.contrasena().isEmpty()) {
            usuario.setContrasena(passwordEncoder.encode(dto.contrasena()));
        }

        usuarioRepository.save(usuario);
        return new DatosDetalleUsuario(usuario.getNombre(), usuario.getCorreoElectronico());
    }

    public Page<DatosDetalleUsuario> listarUsuarios(Pageable pageable) {
        return usuarioRepository.findAll(pageable).map(usuario ->
                new DatosDetalleUsuario(usuario.getNombre(), usuario.getCorreoElectronico()));
    }

    public DatosDetalleUsuario obtenerDetalleUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id));
        return new DatosDetalleUsuario(usuario.getNombre(), usuario.getCorreoElectronico());
    }

    @Transactional
    public void eliminarUsuario(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();

            for (Perfil perfil : usuario.getPerfiles()) {
                perfil.getUsuarios().remove(usuario);
            }
            usuario.getPerfiles().clear();

            usuarioRepository.deleteById(id);
            usuarioRepository.flush();
        } else {
            throw new UsuarioNotFoundException("Usuario no encontrado con id: " + id);
        }
    }

}

