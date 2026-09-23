package com.atividade_to_list.plataformadecursos.DTOs;

import com.atividade_to_list.plataformadecursos.entities.Curso;
import com.atividade_to_list.plataformadecursos.entities.Status;
import com.atividade_to_list.plataformadecursos.entities.Usuario;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MatriculaRequest {
    @NotBlank
    private LocalDateTime dt_matricula;
    @NotBlank
    private Status status;
    private Usuario usuarioId;
    private Curso cursoId;
}
