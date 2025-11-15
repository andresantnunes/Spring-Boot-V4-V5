package com.example.semana9_aula.infra.exception;

// Erro customizado
// Herda de RuntimeException
// Tem um construtor com os valor de RuntimeException
public class InternalServerErrorException extends RuntimeException {

    private String className;
    public InternalServerErrorException(String message, String className) {
        super(message);
        this.className = className;
    }
}
