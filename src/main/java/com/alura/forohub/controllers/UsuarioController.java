package com.alura.forohub.controllers;

import com.alura.forohub.domain.usuario.DatosActualizacionUsuario;
import com.alura.forohub.domain.usuario.DatosDetalleUsuario;
import com.alura.forohub.domain.usuario.DatosRegistroUsuario;
import com.alura.forohub.domain.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<DatosDetalleUsuario> crearUsuario(@Validated @RequestBody DatosRegistroUsuario datos,
                                                            UriComponentsBuilder uriComponentsBuilder) {
        Long idUsuario = usuarioService.crearUsuario(datos);
        DatosDetalleUsuario detalleUsuario = usuarioService.obtenerDetalleUsuario(idUsuario);
        URI url = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(idUsuario).toUri();
        return ResponseEntity.created(url).body(detalleUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DatosDetalleUsuario> actualizarUsuario(@PathVariable Long id,
                                                                 @Validated @RequestBody DatosActualizacionUsuario datosActualizacion) {
        DatosDetalleUsuario detalleUsuario = usuarioService.actualizarUsuario(id, datosActualizacion);
        return ResponseEntity.ok(detalleUsuario);
    }

    @GetMapping
    public ResponseEntity<Page<DatosDetalleUsuario>> listarUsuarios(Pageable pageable) {
        Page<DatosDetalleUsuario> usuarios = usuarioService.listarUsuarios(pageable);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosDetalleUsuario> obtenerDetalleUsuario(@PathVariable Long id) {
        DatosDetalleUsuario detalleUsuario = usuarioService.obtenerDetalleUsuario(id);
        return ResponseEntity.ok(detalleUsuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}

