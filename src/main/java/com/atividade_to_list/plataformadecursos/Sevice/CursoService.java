package com.atividade_to_list.plataformadecursos.Sevice;

import com.atividade_to_list.plataformadecursos.DTOs.CursoRequest;
import com.atividade_to_list.plataformadecursos.DTOs.CursoResponse;
import com.atividade_to_list.plataformadecursos.DTOs.UsuarioRequest;
import com.atividade_to_list.plataformadecursos.DTOs.UsuarioResponse;
import com.atividade_to_list.plataformadecursos.Repository.CursoRepository;
import com.atividade_to_list.plataformadecursos.entities.Curso;
import com.atividade_to_list.plataformadecursos.entities.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {
    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public CursoResponse criarCurso(CursoRequest request) {
        Curso curso = new Curso();
        curso.setTitulo(request.getTitulo());
        curso.setDescricao(request.getDescricao());
        curso.setCargahoraria(request.getCargahoraria());

        Curso cursoSalvo = cursoRepository.save(curso);

        return new CursoResponse(
                cursoSalvo.getId(),
                cursoSalvo.getTitulo(),
                cursoSalvo.getDescricao(),
                cursoSalvo.getCargahoraria()
        );
    }

    public List<CursoResponse> mostrarcursos() {
        return cursoRepository.findAll().stream()
                .map(curso -> new CursoResponse(
                        curso.getId(),
                        curso.getTitulo(),
                        curso.getDescricao(),
                        curso.getCargahoraria()
                ))
                .toList();
    }

    public CursoResponse buscarpoid(Long id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException
                        ("Curso não encontrado com id" + id));
        return new CursoResponse(
                curso.getId(),
                curso.getTitulo(),
                curso.getDescricao(),
                curso.getCargahoraria()
        );

    }

    public String deletar(long id) {
        Optional<Curso> curso = cursoRepository.findById(id);
        if (curso.isEmpty()) {
            return "Curso não existe";
        } else {
            cursoRepository.deleteById(id);
            return "Curso kickado";
        }
    }

    public String atualizarid(Long id, CursoRequest cursoatualizado) {
        Curso cursoexistente = cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("usuario não encontrado: " + id));
            cursoexistente.setTitulo(cursoatualizado.getTitulo());
            cursoexistente.setDescricao(cursoatualizado.getDescricao());
            cursoexistente.setCargahoraria(cursoatualizado.getCargahoraria());
        cursoRepository.save(cursoexistente);
        return "usuario atualizado com sucesso";

    }

}
