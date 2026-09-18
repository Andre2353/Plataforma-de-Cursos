package com.atividade_to_list.plataformadecursos.Sevice;

import com.atividade_to_list.plataformadecursos.DTOs.UsuarioRequest;
import com.atividade_to_list.plataformadecursos.DTOs.UsuarioResponse;
import com.atividade_to_list.plataformadecursos.Repository.UsuarioRepository;
import com.atividade_to_list.plataformadecursos.entities.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
