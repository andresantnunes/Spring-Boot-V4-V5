package com.example.semana9_aula.core.service;

import com.example.semana9_aula.app.dto.TarefaRequest;
import com.example.semana9_aula.app.dto.TarefaResponseBody;
import com.example.semana9_aula.core.mapper.TarefaMapper;
import com.example.semana9_aula.datasource.entity.AlunoEntity;
import com.example.semana9_aula.datasource.entity.TarefaEntity;
import com.example.semana9_aula.datasource.repository.AlunoRepository;
import com.example.semana9_aula.datasource.repository.TarefaRepository;
import com.example.semana9_aula.infra.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TarefaService {
    private final TarefaRepository tarefaRepository;
    private final AlunoRepository alunoRepository;
    private final TarefaMapper tarefaMapper;

    public List<TarefaResponseBody> listarTarefas(String tag) {
        List<TarefaEntity> tarefaEntities = tarefaRepository.findByTag(tag);
        log.info("Total de tarefas encontradas: {}", tarefaEntities.size());

        return tarefaMapper.toReponseList(tarefaEntities);
    }

    // Meios de mapear uma lista para outra lista
    // Temos que alterar em loop, mapeando um item por vez
    // O(n)
//        List<TarefaResponseBody> response =
//                tarefaEntities
//                        .stream()
//                        .map(tarefaEntity -> tarefaMapper.toReponse(tarefaEntity)) // lambda
//                        .toList();
//
//        List<TarefaResponseBody> response =
//                tarefaEntities
//                        .stream()
//                        .map(tarefaMapper::toReponse) // method reference
//                        .toList();

    // Utilizando o MapStruct para fazer o mapeamento de listas
    // Transforma uma lista em outra com um método
    // Preferivel, a menos que dito o contrário
    // ou a menos que haja um problema quando o mapeamento ocorrer

    public TarefaResponseBody salvarTarefa(TarefaRequest tarefaRequest) {
        log.info("Tarefa adicionada: {}", tarefaRequest.getTitulo());

        if ( tarefaRequest.getDuracaoMinutos() < 0 ) {
            throw new IllegalArgumentException("Duração em minutos não pode ser negativa");
        }

        log.info("Buscando aluno por id: {}", tarefaRequest.getAlunoId());
        AlunoEntity aluno = alunoRepository.findById(tarefaRequest.getAlunoId())
                .orElseThrow(() -> new NotFoundException(
                        "Aluno não encontrado para o id: " + tarefaRequest.getAlunoId(),
                        "id_aluno")
                );

        TarefaEntity entity = tarefaMapper.toEntity(tarefaRequest);
        entity.setDataCriacao(LocalDateTime.now());
        entity.setAluno(aluno);

        log.info("Salvando tarefa: {}", tarefaRequest.getTitulo());
        return tarefaMapper.toReponse(
                tarefaRepository.save(entity)
        );
    }

    public TarefaResponseBody buscarPorId(Long id) {
        log.debug("Buscando tarefa pelo id: {}", id); // informações detalhadas, nem sempre aparecem

        TarefaEntity tarefaEntity = tarefaRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(
                        "Tarefa não encontrada",
                        "id")
                );

        log.info("Tarefa encontrada pelo id: {}", id); // informações mais importantes, aparecem mais
        return tarefaMapper.toReponse(
                tarefaEntity
        );
    }
}
