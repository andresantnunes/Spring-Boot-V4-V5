package com.example.semana9_aula.app.controller;

import com.example.semana9_aula.app.dto.AlunoRequest;
import com.example.semana9_aula.app.dto.AlunoResponse;
import com.example.semana9_aula.app.dto.TarefaRequest;
import com.example.semana9_aula.app.dto.TarefaResponseBody;
import com.example.semana9_aula.core.service.AlunoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
@RequiredArgsConstructor
public class AlunoController {

    private final AlunoService alunoService;

    @GetMapping
    public ResponseEntity<List<AlunoResponse>> listarAluno() {
        List<AlunoResponse> resultado = alunoService.listar();

        return ResponseEntity.ok(resultado);
    }

    @PostMapping
    public ResponseEntity adicionarAluno(
            @RequestBody @Valid AlunoRequest alunoRequest
    ) {
        AlunoResponse tarefaSalva = alunoService.salvar(alunoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaSalva);
    }

    @PutMapping(
            path = "/{id}"
    )
    public ResponseEntity atualizaAluno(
            @PathVariable Long id,
            @RequestBody @Valid AlunoRequest alunoRequest
    ) {
        AlunoResponse tarefaSalva = alunoService.atualizar(alunoRequest, id);
        return ResponseEntity.status(HttpStatus.OK).body(tarefaSalva);
    }

    @DeleteMapping(
            path = "/{id}"
    )
    public ResponseEntity deletaAluno(
            @PathVariable Long id
    ) {
        alunoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
