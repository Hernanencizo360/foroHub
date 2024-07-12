package com.alura.forohub.infra.security;

import com.alura.forohub.domain.usuario.Usuario;
import com.alura.forohub.infra.error.TokenGenerationException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String apiSecret; //Contraseña

    public String generarToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer("foroHub")
                    .withSubject(usuario.getCorreoElectronico())
                    .withClaim("id", usuario.getId())
                    .withClaim("nombre", usuario.getNombre())
                    .withExpiresAt(generarTiempoDeExpiracion())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new TokenGenerationException("Error al generar token", exception);
        }
    }

    private Instant generarTiempoDeExpiracion(){
        return LocalDateTime.now().plusHours(4).toInstant(ZoneOffset.of("-05:00"));
    }

    public String getUsernameDelToken(String token) {
        return JWT.require(Algorithm.HMAC256(apiSecret))
                .build()
                .verify(token)
                .getSubject();
    }

    public boolean validarToken(String token) {
        try {
            JWT.require(Algorithm.HMAC256(apiSecret)).build().verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }
}
