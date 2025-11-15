package com.example.semana9_aula.app.dto;

import jakarta.validation.constraints.NotNull;

// Record é voltado para transporte de dados
// Representar uma estrutura de dados imutável
public record TarefaResponseBody(
        Long id,
        String titulo,
        String descricao,
        Long duracaoMinutos,
        Long alunoId
) {
}
