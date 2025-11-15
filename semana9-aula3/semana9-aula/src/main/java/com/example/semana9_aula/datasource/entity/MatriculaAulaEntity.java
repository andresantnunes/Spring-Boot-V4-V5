package com.example.semana9_aula.datasource.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class MatriculaAulaEntity {
//    Podemos ter um id na classe de relacionamento
//    @Id
//    private Long id;

    @EmbeddedId // identificador composto
    private MatriculaAulaEmbedded id;

    private LocalDateTime dataMatricula;
}
