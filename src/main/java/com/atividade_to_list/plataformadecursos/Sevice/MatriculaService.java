package com.atividade_to_list.plataformadecursos.Sevice;


import com.atividade_to_list.plataformadecursos.DTOs.MatriculaRequest;
import com.atividade_to_list.plataformadecursos.DTOs.MatriculaResponse;
import com.atividade_to_list.plataformadecursos.Repository.CursoRepository;
import com.atividade_to_list.plataformadecursos.Repository.MatriculaRepository;
import com.atividade_to_list.plataformadecursos.Repository.UsuarioRepository;
import com.atividade_to_list.plataformadecursos.entities.Matricula;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatriculaService {
    private final MatriculaRepository matriculaRepository;

    public MatriculaService(MatriculaRepository matriculaRepository) {
        this.matriculaRepository = matriculaRepository;
    }

    public MatriculaResponse criarMatricula(MatriculaRequest request) {
        Matricula matricula = new Matricula();
        matricula.setStatus(request.getStatus());
        matricula.setDtMatricula(request.getDt_matricula());

        Matricula salva = matriculaRepository.save(matricula);

        return new MatriculaResponse(
                salva.getId(),
                salva.getDtMatricula(),
                salva.getStatus(),
                salva.getUsuario(),
                salva.getCurso()
        );
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

    public String atualizarid(Long id, MatriculaRequest request) {
        Matricula matriculaexistente = matriculaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matricula não encontrada: " + id));

        // Pega o status novo que veio na requisição e aplica na entidade do banco
        matriculaexistente.setStatus(request.getStatus());

        matriculaRepository.save(matriculaexistente);
        return "matricula atualizado com sucesso";
    }

    public MatriculaResponse buscarpoid(Long id) {
        Matricula matricula = matriculaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matricula não encontrado com id" + id));

        // Mantida a mesma ordem de 5 parâmetros usada no mostrarUsuarios para não dar erro de compilação
        return new MatriculaResponse(
                matricula.getId(),
                matricula.getDtMatricula(),
                matricula.getStatus(),
                matricula.getUsuario(),
                matricula.getCurso()
        );
    }
}
