package com.alura.forohub.controllers;

import com.alura.forohub.domain.topico.DatosRegistroTopico;
import com.alura.forohub.domain.topico.Topico;
import com.alura.forohub.domain.topico.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoServicio;

    @PostMapping
    public void crearTopico(@Valid @RequestBody DatosRegistroTopico topico) {
        System.out.println(topico);
    }
}

