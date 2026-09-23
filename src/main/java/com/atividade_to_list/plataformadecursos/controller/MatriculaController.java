package com.atividade_to_list.plataformadecursos.controller;

import com.atividade_to_list.plataformadecursos.DTOs.CursoResponse;
import com.atividade_to_list.plataformadecursos.DTOs.MatriculaRequest;
import com.atividade_to_list.plataformadecursos.DTOs.MatriculaResponse;
import com.atividade_to_list.plataformadecursos.Sevice.MatriculaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("curso")
public class MatriculaController {

    private final MatriculaService matriculaService;

    public MatriculaController(MatriculaService matriculaService) {
        this.matriculaService = matriculaService;
    }


    @PostMapping
    public ResponseEntity<MatriculaResponse> criarCurso(@Valid @RequestBody MatriculaRequest request) {
        MatriculaResponse novaMatricula = matriculaService.criarMatricula(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaMatricula);
    }

}

