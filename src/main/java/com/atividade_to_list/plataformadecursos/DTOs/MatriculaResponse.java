package com.atividade_to_list.plataformadecursos.DTOs;

import com.atividade_to_list.plataformadecursos.entities.Curso;
import com.atividade_to_list.plataformadecursos.entities.Status;
import com.atividade_to_list.plataformadecursos.entities.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MatriculaResponse {
    private Long id;
    private LocalDateTime dtMatricula;
    private Status status;
    private Long usuarioId;
    private String usuarioNome;
    private Long cursoId;
    private String cursoTitulo;

}
