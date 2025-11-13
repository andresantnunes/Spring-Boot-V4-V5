package com.example.semana9_aula.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Data // Getters e Setters
@ToString
public class TarefaRequest {
    @NotBlank // String não pode ser vazia ou nula
    private String titulo;
    private String descricao;
    @NotNull // objeto não pode ser nulo
    private Long duracaoMinutos;
    @NotEmpty // coleção não pode ser vazia ou nula
    private List<String> tags;
}
