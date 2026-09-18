package com.atividade_to_list.plataformadecursos.Sevice;

import com.atividade_to_list.plataformadecursos.DTOs.UsuarioRequest;
import com.atividade_to_list.plataformadecursos.DTOs.UsuarioResponse;
import com.atividade_to_list.plataformadecursos.Repository.UsuarioRepository;
import com.atividade_to_list.plataformadecursos.entities.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioRequest criarUsuario(UsuarioRequest request) {
        Usuario usuario = new Usuario();
        usuario.setName(request.getName());
        usuario.setEmail(request.getEmail());
        usuario.setSenha(request.getSenha());
        usuarioRepository.save(usuario);
        return request;
    }

    public List<UsuarioResponse> mostrarUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(usuario -> new UsuarioResponse(
                        usuario.getId(),
                        usuario.getName(),
                        usuario.getEmail(),
                        usuario.getSenha()
                ))
                .toList();
    }
    public String deletar(long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isEmpty()) {
            return "Usuário não existe";
        } else {
            usuarioRepository.deleteById(id);
            return "Usuário kickado";
        }
    }
    public String atualizarid(Long id, Usuario usuarioatualizado) {
        Usuario usuarioexistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("usuario não encontrado: " + id));

        usuarioexistente.setName(usuarioatualizado.getName());
        usuarioexistente.setEmail(usuarioatualizado.getEmail());
        usuarioexistente.setSenha(usuarioatualizado.getSenha());

        usuarioRepository.save(usuarioexistente);
        return "usuario atualizado com sucesso";
    }
}
