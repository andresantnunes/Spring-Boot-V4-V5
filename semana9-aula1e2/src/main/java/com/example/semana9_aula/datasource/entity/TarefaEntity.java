package com.example.semana9_aula.datasource.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tarefa")
@Data
@NoArgsConstructor
public class TarefaEntity {

    public TarefaEntity(String titulo, String descricao, LocalDateTime dataCriacao) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
    }

    // toda vez que eu criar um objeto do tipo TarefaEntity
    // e salvar esse objeto, eu vou adicionar
    // um valor unico a esse campo do objeto
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String titulo;

    @Column(length = 500)
    private String descricao;
    private LocalDateTime dataCriacao;

    @Column(nullable = false)
    private Long duracaoMinutos;
    private List<String> tags;
}
