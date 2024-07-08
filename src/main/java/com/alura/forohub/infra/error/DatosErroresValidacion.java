package com.alura.forohub.infra.error;

import org.springframework.validation.FieldError;

public record DatosErroresValidacion(String campo, String error) {
    public DatosErroresValidacion(FieldError error){
        this(error.getField(), error.getDefaultMessage());
    }
}
