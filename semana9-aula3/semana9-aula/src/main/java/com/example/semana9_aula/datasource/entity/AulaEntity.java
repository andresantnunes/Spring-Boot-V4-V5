package com.example.semana9_aula.datasource.entity;

import jakarta.persistence.*;
import lombok.Data;

@Table
@Entity
@Data
public class AulaEntity {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String assunto;
    private String nomeProfessor; //adicione um professor e o CRUD para ele

//    @OneToMany // Uma aula para muitos alunos
//    private List<AlunoEntity> alunos;
}
