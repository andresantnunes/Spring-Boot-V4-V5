package com.example.semana9_aula.core.service;

import com.example.semana9_aula.app.dto.TarefaRequest;
import com.example.semana9_aula.core.mapper.TarefaMapper;
import com.example.semana9_aula.datasource.entity.TarefaEntity;
import com.example.semana9_aula.datasource.repository.TarefaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService {
    private final TarefaRepository tarefaRepository;
    private final TarefaMapper tarefaMapper;

    public List<TarefaEntity> listarTarefas() {
        return tarefaRepository.findAll();
    }

    public TarefaEntity salvarTarefa(TarefaRequest tarefaRequest) {
        System.out.println("Tarefa adicionada: " + tarefaRequest.getTitulo());

        TarefaEntity entity = tarefaMapper.toEntity(tarefaRequest);
        entity.setDataCriacao(LocalDateTime.now());
        return tarefaRepository.save(entity);
    }
}
