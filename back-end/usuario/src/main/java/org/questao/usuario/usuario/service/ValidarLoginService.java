package org.questao.usuario.usuario.service;

import org.questao.usuario.Erros.usuario.ErroAoTentarValidarLoginUsuario;
import org.questao.usuario.usuario.dominio.Usuario;
import org.questao.usuario.usuario.infraestrutura.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ValidarLoginService {

    private static final Logger log = LoggerFactory.getLogger(ValidarLoginService.class);
    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public ValidarLoginService(UsuarioRepository usuarioRepository, BCryptPasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario validarLogin(String email, String senha) {
        try {
            Usuario usuario = usuarioRepository.buscarPorEmail(email);
            if (usuario == null || !passwordEncoder.matches(senha, usuario.getSenha().senha())) throw new RuntimeException("Email ou senha inválidos");
            return usuario;
        }
        catch (Exception e) {
            log.error("Erro ao tentar validar login usuario: ", e.getMessage());
            throw new ErroAoTentarValidarLoginUsuario(e);
        }
    }
}
