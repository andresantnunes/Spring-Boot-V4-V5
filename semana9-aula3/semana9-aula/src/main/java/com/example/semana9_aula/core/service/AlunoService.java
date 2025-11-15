package com.example.semana9_aula.core.service;

import com.example.semana9_aula.app.dto.AlunoRequest;
import com.example.semana9_aula.app.dto.AlunoResponse;
import com.example.semana9_aula.core.mapper.AlunoMapper;
import com.example.semana9_aula.datasource.entity.AlunoEntity;
import com.example.semana9_aula.datasource.repository.AlunoRepository;
import com.example.semana9_aula.infra.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AlunoService {
    private final AlunoRepository alunoRepository;
    private final AlunoMapper alunoMapper;

    public List<AlunoResponse> listar() {
        log.info("Listando alunos");
        return alunoMapper
                .toResponseList(
                    alunoRepository.findAll()
        );
    }

    public AlunoResponse salvar(AlunoRequest alunoRequest) {
        AlunoResponse response =
                alunoMapper.toResponse(
                    alunoRepository.save(
                        alunoMapper.toEntity(alunoRequest)
                    )
                );

        // record não usar get
        // ele usa o nome do atributo diretamente, seguido por ()
        log.info("Aluno salvo: {}", response.nome());
        return response;
    }

    public AlunoResponse atualizar(AlunoRequest alunoRequest, Long id) {
        log.info("Atualizando aluno: {}", alunoRequest.nome());

        // 1. Verificar se o aluno existe
        AlunoEntity alunoExistente = alunoRepository.findById(id)
                .orElseThrow(
                        () ->
                        new NotFoundException("Aluno não encontrado para o id: " + id, "id_aluno")
                );

        AlunoEntity alunoAtualizado = alunoMapper.toEntity(alunoRequest);

        // 2. Juntar os dados do aluno existente com o atualizado
//        alunoMapper.merge(alunoAtualizado, alunoExistente);
        alunoExistente.setNome(alunoAtualizado.getNome());
        alunoExistente.setIdade(alunoAtualizado.getIdade());

        // 3. Salvar o aluno atualizado
        // alunoExistente é quem tem a referencia do aluno original no banco
        // para atualizar uma linha do banco de dados, usamos o objeto que foi buscado
        AlunoEntity alunoSalvo = alunoRepository.save(alunoExistente);

        log.info("Aluno atualizado: {}", alunoSalvo.getNome());
        return alunoMapper.toResponse(alunoSalvo);
    }

    public void deletar(Long id) {
        log.info("Deletando aluno de id: {}", id);
        alunoRepository.deleteById(id);
    }
}
