package com.atividade_to_list.plataformadecursos.Sevice;

import com.atividade_to_list.plataformadecursos.DTOs.MatriculaRequest;
import com.atividade_to_list.plataformadecursos.DTOs.MatriculaResponse;
import com.atividade_to_list.plataformadecursos.DTOs.UsuarioRequest;
import com.atividade_to_list.plataformadecursos.DTOs.UsuarioResponse;
import com.atividade_to_list.plataformadecursos.Repository.MatriculaRepository;
import com.atividade_to_list.plataformadecursos.entities.Matricula;
import com.atividade_to_list.plataformadecursos.entities.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatriculaService {
    private final MatriculaRepository matriculaRepository;

    public MatriculaService(MatriculaRepository matriculaRepository) {
        this.matriculaRepository = matriculaRepository;
    }
    public MatriculaRequest criarMatricula(MatriculaRequest request) {
        Matricula matricula = new Matricula();
        matricula.setStatus(request.getStatus());
        matricula.setDtMatricula(request.getDt_matricula());

        matriculaRepository.save(matricula);
        return request;
    }
    public List<MatriculaResponse> mostrarUsuarios() {
        return matriculaRepository.findAll().stream()
                .map(matricula -> new MatriculaResponse(
                        matricula.getId(),
                        matricula.getDtMatricula(),
                        matricula.getStatus(),
                        matricula.getUsuario(),
                        matricula.getCurso()

                ))
                .toList();
    }
    public String deletar(long id) {
        Optional<Matricula> matricula = matriculaRepository.findById(id);
        if (matricula.isEmpty()) {
            return "Matricula não existe";
        } else {
            matriculaRepository.deleteById(id);
            return "Matricula kickada";
        }
    }
    public String atualizarid(Long id, Matricula matriculaatualizada) {
        Matricula matriculaexistente = matriculaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matricula não encontrada: " + id));

       matriculaatualizada.setStatus(matriculaexistente.getStatus());

        matriculaRepository.save(matriculaexistente);
        return "matricula atualizado com sucesso";
    }
}
