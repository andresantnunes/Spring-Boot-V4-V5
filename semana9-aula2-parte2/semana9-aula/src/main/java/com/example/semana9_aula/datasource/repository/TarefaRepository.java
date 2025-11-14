package com.example.semana9_aula.datasource.repository;

import com.example.semana9_aula.datasource.entity.TarefaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // identifica uma interface que integra com o banco de dados
// vem do Spring Data JPA
public interface TarefaRepository extends JpaRepository<TarefaEntity, Long> {
}
