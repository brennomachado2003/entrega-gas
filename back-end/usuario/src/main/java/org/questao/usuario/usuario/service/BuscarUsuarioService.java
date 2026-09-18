package org.questao.usuario.usuario.service;

import org.questao.usuario.usuario.dominio.Usuario;
import org.questao.usuario.usuario.infraestrutura.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class BuscarUsuarioService {

    private final UsuarioRepository usuarioRepository;

    public BuscarUsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario buscarPorId(Long idUsuario) {
        return usuarioRepository.buscar(idUsuario);
    }

}
