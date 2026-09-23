package com.atividade_to_list.plataformadecursos.Sevice;

import com.atividade_to_list.plataformadecursos.DTOs.MatriculaRequest;
import com.atividade_to_list.plataformadecursos.DTOs.UsuarioRequest;
import com.atividade_to_list.plataformadecursos.Repository.MatriculaRepository;
import com.atividade_to_list.plataformadecursos.entities.Matricula;
import com.atividade_to_list.plataformadecursos.entities.Usuario;
import org.springframework.stereotype.Service;

@Service
public class MatriculaService {
    private final MatriculaRepository matriculaRepository;

    public MatriculaService(MatriculaRepository matriculaRepository) {
        this.matriculaRepository = matriculaRepository;
    }
    public MatriculaRequest criarMatricula(MatriculaRequest request) {
        Matricula matricula = new Matricula();
        matricula.setStatus(request.getStatus());

        matriculaRepository.save(matricula);
        return request;
    }
}
