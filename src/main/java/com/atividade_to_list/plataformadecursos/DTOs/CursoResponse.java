package com.atividade_to_list.plataformadecursos.DTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CursoResponse {
    private Long id;
    private String titulo;
    private String descricao;
    @NotBlank
    private String cargahoraria;

}
