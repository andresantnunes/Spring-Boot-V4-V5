package com.example.semana9_aula.app.controller;

import com.example.semana9_aula.app.dto.TarefaRequest;
import com.example.semana9_aula.datasource.entity.TarefaEntity;
import com.example.semana9_aula.core.service.TarefaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller  - anotação genérica de controlador
//@ResponseBody - indica que a resposta é REST

@RestController // Junta as anotações de @Controller e @ResponseBody
@RequestMapping("/tarefas")
@RequiredArgsConstructor // construtor com todos os atributos finais (final)
public class TarefaController {

    private final TarefaService tarefaService;

//    public TarefaController(TarefaService tarefaService) {
//        this.tarefaService = tarefaService;
//    }

    //    @Autowired // injeção de dependência por atributo
//    // gera reflexão para instanciar o objeto
//    private TarefaRepository tarefaRepository;
//    Não é recomendado para o código normal


    @GetMapping
    public ResponseEntity<List<TarefaEntity>> listarTarefas() {
        List<TarefaEntity> resultado = tarefaService.listarTarefas();

        return ResponseEntity.ok(resultado);
    }

    @PostMapping
    public ResponseEntity adicionarTarefa(
            @RequestBody TarefaRequest tarefa
    ){
        TarefaEntity tarefaSalva = tarefaService.salvarTarefa(tarefa);
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaSalva);

    }
}
