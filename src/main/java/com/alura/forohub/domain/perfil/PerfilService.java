package com.alura.forohub.domain.perfil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PerfilService {

    @Autowired
    private PerfilRepository perfilRepository;

    public Perfil obtenerPerfilUsuario() {
        return perfilRepository.findByNombre(NombrePerfil.USUARIO)
                .orElseThrow(() -> new IllegalArgumentException("Perfil USUARIO no encontrado"));
    }

    public void saveAllPerfiles(Set<Perfil> perfiles) {
        perfilRepository.saveAll(perfiles);
    }
}
