package com.example.semana9_aula.core.mapper;

import com.example.semana9_aula.app.dto.AlunoRequest;
import com.example.semana9_aula.app.dto.AlunoResponse;
import com.example.semana9_aula.datasource.entity.AlunoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.lang.annotation.Target;
import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE // ignora valores nulos ao fazer merge
)
public interface AlunoMapper {
    AlunoEntity toEntity(AlunoRequest request);
    AlunoResponse toResponse(AlunoEntity request);
    List<AlunoResponse> toResponseList(List<AlunoEntity> request);

    // junta os dados de alunoExistente em alunoAtualizado
    void merge(@MappingTarget AlunoEntity alunoAtualizado, AlunoEntity alunoExistente);
}
