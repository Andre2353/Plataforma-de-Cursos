package com.atividade_to_list.plataformadecursos.Sevice;


import com.atividade_to_list.plataformadecursos.DTOs.CursoResponse;
import com.atividade_to_list.plataformadecursos.DTOs.MatriculaRequest;
import com.atividade_to_list.plataformadecursos.DTOs.MatriculaResponse;

import com.atividade_to_list.plataformadecursos.Repository.CursoRepository;
import com.atividade_to_list.plataformadecursos.Repository.MatriculaRepository;
import com.atividade_to_list.plataformadecursos.Repository.UsuarioRepository;
import com.atividade_to_list.plataformadecursos.entities.Curso;
import com.atividade_to_list.plataformadecursos.entities.Matricula;
import com.atividade_to_list.plataformadecursos.entities.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatriculaService {
    private final MatriculaRepository matriculaRepository;
    private final UsuarioRepository usuarioRepository;
    private final CursoRepository cursoRepository;

    public MatriculaService(MatriculaRepository matriculaRepository,
                            UsuarioRepository usuarioRepository,
                            CursoRepository cursoRepository) {
        this.matriculaRepository = matriculaRepository;
        this.usuarioRepository = usuarioRepository;
        this.cursoRepository = cursoRepository;
    }

    public MatriculaResponse criarMatricula(MatriculaRequest request) {
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado: " + request.getUsuarioId()));
        Curso curso = cursoRepository.findById(request.getCursoId())
                .orElseThrow(() -> new RuntimeException("Curso não encontrado: " + request.getCursoId()));

        Matricula matricula = new Matricula();
        matricula.setStatus(request.getStatus());
        matricula.setDtMatricula(request.getDtMatricula());
        matricula.setUsuario(usuario);
        matricula.setCurso(curso);

        Matricula salva = matriculaRepository.save(matricula);

        return new MatriculaResponse(
                salva.getId(),
                salva.getDtMatricula(),
                salva.getStatus(),
                salva.getUsuario().getId(),
                salva.getUsuario().getName(),
                salva.getCurso().getId(),
                salva.getCurso().getTitulo()
        );
    }

    public List<MatriculaResponse> mostrarUsuarios() {
        return matriculaRepository.findAll().stream()
                .map(salva-> new MatriculaResponse(
                        salva.getId(),
                        salva.getDtMatricula(),
                        salva.getStatus(),
                        salva.getUsuario().getId(),
                        salva.getUsuario().getName(),
                        salva.getCurso().getId(),
                        salva.getCurso().getTitulo()
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


        matriculaexistente.setStatus(request.getStatus());

        matriculaRepository.save(matriculaexistente);
        return "matricula atualizado com sucesso";
    }

    public MatriculaResponse buscarpoid(Long id) {
        Matricula matricula = matriculaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matricula não encontrado com id" + id));

        return new MatriculaResponse(
                matricula.getId(),
                matricula.getDtMatricula(),
                matricula.getStatus(),
                matricula.getUsuario().getId(),
                matricula.getUsuario().getName(),
                matricula.getCurso().getId(),
                matricula.getCurso().getTitulo()
        );
    }


}
