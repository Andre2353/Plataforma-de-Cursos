package com.atividade_to_list.plataformadecursos.Repository;

import com.atividade_to_list.plataformadecursos.entities.Matricula;
import com.atividade_to_list.plataformadecursos.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
    List<Matricula> findByUsuarioId(Long usuarioId);
    List<Matricula> findByCursoId(Long cursoId);
}
