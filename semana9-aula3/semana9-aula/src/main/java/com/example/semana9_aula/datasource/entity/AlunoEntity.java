package com.example.semana9_aula.datasource.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Table
@Entity
@Data
public class AlunoEntity {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer idade;

//    @ManyToMany // Muitos alunos para muitas aulas
//    @JoinTable(
//            name = "aluno_aula", // Nome da tabela intermediaria
//            joinColumns = @JoinColumn(name = "aluno_id"), // Chave estrangeira para AlunoEntity
//            inverseJoinColumns = @JoinColumn(name = "aula_id") // Chave estrangeira para AulaEntity
//    )
//    private Set<AulaEntity> aulas = new HashSet<AulaEntity>();
}
