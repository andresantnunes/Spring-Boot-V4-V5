package com.example.semana9_aula.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Data // Getters e Setters
@ToString
public class TarefaRequest {
    @NotBlank(message = "Titulo não pode estar em branco") // String não pode ser vazia ou nula
    // message - subistitui a mensagem padrão do validation
    private String titulo;
    private String descricao;

    @NotNull(message = "Duração em minutos não pode estar nulo") // objeto não pode ser nulo
    private Long duracaoMinutos;

    @NotEmpty(message = "Tag não pode estar vazio") // coleção não pode ser vazia ou nula
    // Se fosse NotNull, ele permite listas vazias
    private List<String> tags;

    @NotNull(message = "Aluno não pode estar nulo")
    private Long alunoId;

    private ExemploDto exemplo;
}
