package com.example.semana9_aula.app.controller;

import com.example.semana9_aula.app.dto.TarefaRequest;
import com.example.semana9_aula.app.dto.TarefaResponseBody;
import com.example.semana9_aula.core.service.TarefaService;
import jakarta.validation.Valid;
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
                        // método recomendado para fazer injeção de dependência
public class TarefaController {

    private final TarefaService tarefaService;

//    public TarefaController(TarefaService tarefaService) {
//        this.tarefaService = tarefaService;
//    }

//    @Autowired // injeção de dependência por atributo
//               // gera reflexão para instanciar o objeto
//    private TarefaRepository tarefaRepository;
//    Não é recomendado para o código normal

    @GetMapping
    public ResponseEntity<List<TarefaResponseBody>> listarTarefas(
            @RequestParam(name = "tag",required = false) String tag
    ) {
        List<TarefaResponseBody> resultado = tarefaService.listarTarefas(tag);

        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaResponseBody> buscarTarefaPorId(
            @PathVariable("id") // o parenteses é necessário quando o campos e a varivale tem nomes diferentes
            Long id
    ) {
        TarefaResponseBody resultado = tarefaService.buscarPorId(id);

        return ResponseEntity.ok(resultado);
    }

    @PostMapping
    public ResponseEntity adicionarTarefa(
            @RequestBody // indica um corpo de requisição que será convertido de json para objeto
            @Valid       // indica que o json deve ser validado
            TarefaRequest tarefa // objeto que representa o corpo da requisição
    ){
        TarefaResponseBody tarefaSalva = tarefaService.salvarTarefa(tarefa);
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaSalva);
    }
}
