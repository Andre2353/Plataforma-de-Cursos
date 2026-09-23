package com.atividade_to_list.plataformadecursos.controller;

import com.atividade_to_list.plataformadecursos.DTOs.CursoRequest;
import com.atividade_to_list.plataformadecursos.DTOs.CursoResponse;
import com.atividade_to_list.plataformadecursos.Sevice.CursoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("curso")
public class cursoController {

    private final CursoService cursoService;

    public cursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }


    @PostMapping
    public ResponseEntity<CursoResponse> criarCurso(@Valid @RequestBody CursoRequest request) {
        CursoResponse novoCurso = cursoService.criarCurso(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCurso);
    }

    @GetMapping
    public ResponseEntity<List<CursoResponse>> mostrarCursos() {
        List<CursoResponse> cursos = cursoService.mostrarcursos();
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoResponse> buscarPorId(@PathVariable Long id) {
        CursoResponse curso = cursoService.buscarpoid(id);
        return ResponseEntity.ok(curso);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCurso(@PathVariable Long id) {
        cursoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

