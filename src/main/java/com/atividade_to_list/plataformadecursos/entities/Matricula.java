package com.atividade_to_list.plataformadecursos.entities;

import jakarta.persistence.*;
import jdk.jshell.Snippet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "matriculas")
@AllArgsConstructor
@NoArgsConstructor
public class Matricula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dt_matricula;
    private Status status;
    @ManyToOne
    private Usuario usuario;
    @ManyToOne
    private Curso curso;
}
