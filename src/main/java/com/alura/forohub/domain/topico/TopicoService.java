package com.alura.forohub.domain.topico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepositorio;

    public Topico crearTopico(DatosRegistroTopico datosRegistroTopico) {
        //TODO continuar con la creacion y persistencia;
        Topico topico = new Topico();
        return topico;
    }
}

