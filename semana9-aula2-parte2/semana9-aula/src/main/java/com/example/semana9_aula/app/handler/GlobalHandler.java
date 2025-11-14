package com.example.semana9_aula.app.handler;

import com.example.semana9_aula.infra.exception.NotFoundException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@Slf4j // Server para adicionar Logs dedicados, adicionar o item "log"
@ControllerAdvice // indica para o Spring que essa classe trata exceções globalmente
// é um processo paralelo ao controller normais
public class GlobalHandler {
    // indica o erro que vamos tratar
    // erro do Java
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e){
        log.error("Erro inesperado", e); // log de erro é extremamente importante e sempre aparece

        ErrorResponse errorResponse = ErrorResponse.create(
                e,
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Erro interno do servidor"
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse);
    }

    // Erro da lib validations
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(
            ConstraintViolationException e
    ){
        List<String> errors =
                e.getConstraintViolations()
                        .stream()
                        .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .toList();

        log.error("Erros de validação: " + errors);

        ErrorResponse errorResponse = ErrorResponse.create(
                e,
                HttpStatus.BAD_REQUEST,
                "Erro ao processar a requisição"
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(
            MethodArgumentNotValidException e
    ){
        List<String> errors =
                e.getAllErrors()
                        .stream()
                        .map(oE -> oE.getObjectName() + ": " + oE.getDefaultMessage())
                .toList();

        log.error("Erros de validação: " + errors);

        ErrorResponse errorResponse = ErrorResponse.create(
                e,
                HttpStatus.BAD_REQUEST,
                "Erro ao processar a requisição"
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(
            IllegalArgumentException e
    ){
        log.error("Erros de validação: " + e.getMessage());

        ErrorResponse errorResponse = ErrorResponse.create(
                e,
                HttpStatus.BAD_REQUEST,
                "Erro ao processar a requisição"
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(
            NotFoundException e
    ){
        log.error("Erros de busca: " + e.getField() +", "+ e.getMessage());

        ErrorResponse errorResponse = ErrorResponse.create(
                e,
                HttpStatus.NOT_FOUND,
                "Erro ao procurar recurso"
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }

}
