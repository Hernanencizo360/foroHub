package com.alura.forohub.domain.usuario;

import com.alura.forohub.infra.error.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public DatosListarUsuario convertirADatosListarUsuario(Usuario usuario) {
        return new DatosListarUsuario(usuario.getNombre(), usuario.getCorreoElectronico());
    }

    public Usuario obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id));
    }

}

