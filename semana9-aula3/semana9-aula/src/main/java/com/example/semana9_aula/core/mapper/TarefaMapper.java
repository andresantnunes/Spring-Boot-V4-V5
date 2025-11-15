package com.example.semana9_aula.core.mapper;

import com.example.semana9_aula.app.dto.TarefaRequest;
import com.example.semana9_aula.app.dto.TarefaResponseBody;
import com.example.semana9_aula.datasource.entity.TarefaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

// É um mapper
// Esse mapper é para o Spring
// É um component, ou seja permite ser injetado em outras classes do Spring
@Mapper(componentModel = "spring")
public interface TarefaMapper {
    // Busque todos os dados da TarefaRequest e coloque na TarefaEntity
    TarefaEntity toEntity(TarefaRequest request);

    // alvo = alunoId do TarefaResponseBody
    // fonte = request.aluno.id da TarefaEntity
    // tranferir o valor diretamente entre os campos
    @Mapping(target = "alunoId", source = "request.aluno.id")
    TarefaResponseBody toReponse(TarefaEntity request);

    // toResponseList reutiliza os Mappings do toReponse
    List<TarefaResponseBody> toReponseList(List<TarefaEntity> tarefaEntities);
}
