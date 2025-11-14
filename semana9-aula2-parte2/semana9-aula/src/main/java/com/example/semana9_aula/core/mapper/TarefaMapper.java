package com.example.semana9_aula.core.mapper;

import com.example.semana9_aula.app.dto.TarefaRequest;
import com.example.semana9_aula.datasource.entity.TarefaEntity;
import org.mapstruct.Mapper;

// É um mapper
// Esse mapper é para o Spring
// É um component, ou seja permite ser injetado em outras classes do Spring
@Mapper(componentModel = "spring")
public interface TarefaMapper {
    // Busque todos os dados da TarefaRequest e coloque na TarefaEntity
    TarefaEntity toEntity(TarefaRequest request);
}
