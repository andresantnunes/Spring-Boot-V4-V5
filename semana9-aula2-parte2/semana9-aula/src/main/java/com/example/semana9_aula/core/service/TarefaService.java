package com.example.semana9_aula.core.service;

import com.example.semana9_aula.app.dto.TarefaRequest;
import com.example.semana9_aula.core.mapper.TarefaMapper;
import com.example.semana9_aula.datasource.entity.TarefaEntity;
import com.example.semana9_aula.datasource.repository.TarefaRepository;
import com.example.semana9_aula.infra.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TarefaService {
    private final TarefaRepository tarefaRepository;
    private final TarefaMapper tarefaMapper;

    public List<TarefaEntity> listarTarefas() {
        return tarefaRepository.findAll();
    }

    public TarefaEntity salvarTarefa(TarefaRequest tarefaRequest) {
        log.info("Tarefa adicionada: {}", tarefaRequest.getTitulo());

        if ( tarefaRequest.getDuracaoMinutos() < 0 ) {
            throw new IllegalArgumentException("Duração em minutos não pode ser negativa");
        }

        TarefaEntity entity = tarefaMapper.toEntity(tarefaRequest);
        entity.setDataCriacao(LocalDateTime.now());
        log.info("Salvando tarefa: {}", tarefaRequest.getTitulo());
        return tarefaRepository.save(entity);
    }

    public TarefaEntity buscarPorId(Long id) {
        log.debug("Buscando tarefa pelo id: {}", id); // informações detalhadas, nem sempre aparecem

        TarefaEntity tarefaEntity = tarefaRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(
                        "Tarefa não encontrada",
                        "id")
                );

        log.info("Tarefa encontrada pelo id: {}", id); // informações mais importantes, aparecem mais
        return tarefaEntity;
    }
}
