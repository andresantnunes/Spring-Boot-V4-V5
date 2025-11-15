package com.example.semana9_aula.infra.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {

    private String field;

    public NotFoundException(String message, String field) {
        super(message);
        this.field = field;
    }
}
