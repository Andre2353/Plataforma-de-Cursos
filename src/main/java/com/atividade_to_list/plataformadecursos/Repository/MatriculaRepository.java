package com.atividade_to_list.plataformadecursos.Repository;

import com.atividade_to_list.plataformadecursos.entities.Matricula;
import com.atividade_to_list.plataformadecursos.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
}
