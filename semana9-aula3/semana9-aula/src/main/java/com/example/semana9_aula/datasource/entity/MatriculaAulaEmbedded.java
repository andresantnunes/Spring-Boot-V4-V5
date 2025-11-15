package com.example.semana9_aula.datasource.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;

// Embbeded Id - ID composto
// Uma classe que representa a chave composta
// para a entidade de relacionamento MatriculaAulaEntity
@Embeddable
public class MatriculaAulaEmbedded {
    @ManyToOne
    private AlunoEntity aluno;

    @ManyToOne
    private AulaEntity aula;
}
