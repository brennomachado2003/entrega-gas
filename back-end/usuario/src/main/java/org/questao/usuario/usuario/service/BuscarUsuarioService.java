package org.questao.usuario.usuario.service;

import org.questao.usuario.Erros.usuario.ErroAoBusarUsuarioPeloId;
import org.questao.usuario.usuario.dominio.Usuario;
import org.questao.usuario.usuario.infraestrutura.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BuscarUsuarioService {

    private static final Logger log = LoggerFactory.getLogger(BuscarUsuarioService.class);
    private final UsuarioRepository usuarioRepository;

    public BuscarUsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario buscarPorId(Long idUsuario) {
        try {
            return usuarioRepository.buscar(idUsuario);
        }
        catch (Exception e) {
            log.error("Erro ao tentar buscar o usuario: ", e.getMessage());
            throw new ErroAoBusarUsuarioPeloId(e);

        }
    }

}
