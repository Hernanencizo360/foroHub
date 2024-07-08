package com.alura.forohub.controllers;

import com.alura.forohub.domain.usuario.DatosAutenticationUsuario;
import com.alura.forohub.domain.usuario.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutenticationUsuario datosAutenticationUsuario) {
        var authToken = new UsernamePasswordAuthenticationToken(datosAutenticationUsuario.correElectronico(), datosAutenticationUsuario.contrasena());
        authenticationManager.authenticate(authToken);


        return ResponseEntity.ok().build();
    }
}
