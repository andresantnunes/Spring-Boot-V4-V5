package com.example.semana9_aula.datasource.repository;

import com.example.semana9_aula.datasource.entity.TarefaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // identifica uma interface que integra com o banco de dados
// vem do Spring Data JPA
public interface TarefaRepository extends JpaRepository<TarefaEntity, Long> {

    // JPQL - Java Persistence Query Language
    // consulta personalizada para buscar se a tarefas contem a tag pesquisada
    @Query("SELECT t FROM TarefaEntity t WHERE (:tag) in (t.tags)")
    List<TarefaEntity> findByTag(@Param("tag") String tag);

    List<TarefaEntity> findByTitulo(String titulo); // cria uma query para buscar por titulo

    //Query Nativa do SQL
    @Query(
            value = "SELECT * FROM tarefa t WHERE t.descricao LIKE %:descricao%",
            nativeQuery = true
    )
    List<TarefaEntity> findByDescricao(@Param("descricao") String descricao);


}
