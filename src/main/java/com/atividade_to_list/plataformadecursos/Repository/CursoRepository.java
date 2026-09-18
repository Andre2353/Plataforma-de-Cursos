package com.atividade_to_list.plataformadecursos.Repository;

import com.atividade_to_list.plataformadecursos.entities.Curso;
import com.atividade_to_list.plataformadecursos.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
