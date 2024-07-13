package com.alura.forohub.controllers;

import com.alura.forohub.domain.topico.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    public ResponseEntity<DatosDetalleTopico> crearTopico(@Validated @RequestBody DatosRegistroTopico datos,
                                                          UriComponentsBuilder uriComponentsBuilder) {
        Long idTopico = topicoService.crearTopico(datos);

        DatosDetalleTopico detalleTopico = topicoService.obtenerDetalleTopico(idTopico);
        URI url =uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(idTopico).toUri();

        return ResponseEntity.created(url).body(detalleTopico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DatosDetalleTopico> actualizarTopico(
            @PathVariable Long id,
            @Validated @RequestBody DatosActualizacionTopico datosActualizacion) {
        DatosDetalleTopico detalleTopico = topicoService.actualizarTopico(id, datosActualizacion);
        return ResponseEntity.ok(detalleTopico);
    }

    @GetMapping
    public ResponseEntity<Page<DatosDetalleTopico>> listarTopicos(@PageableDefault(size = 10,
            sort = "fechaCreacion", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<DatosDetalleTopico> topicos = topicoService.listarTopicos(pageable);
        return ResponseEntity.ok(topicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosDetalleTopico> obtenerDetalleTopico(@PathVariable Long id) {
        DatosDetalleTopico detalleTopico = topicoService.obtenerDetalleTopico(id);
        return ResponseEntity.ok(detalleTopico);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        topicoService.eliminarTopico(id);
        return ResponseEntity.noContent().build();
    }
}

