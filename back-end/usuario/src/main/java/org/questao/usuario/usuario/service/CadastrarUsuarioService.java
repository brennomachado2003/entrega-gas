package org.questao.usuario.usuario.service;

import org.questao.usuario.Erros.usuario.ErroAoCadastrarUsuario;
import org.questao.usuario.usuario.dominio.Senha;
import org.questao.usuario.usuario.dominio.Usuario;
import org.questao.usuario.usuario.infraestrutura.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CadastrarUsuarioService {

    private static final Logger log = LoggerFactory.getLogger(CadastrarUsuarioService.class);
    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    public CadastrarUsuarioService(UsuarioRepository usuarioRepository, BCryptPasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario cadastrar(Usuario usuario) {
        try {
            String senhaCriptografada = passwordEncoder.encode(usuario.getSenha().senha());
            usuario.setSenha(new Senha(senhaCriptografada));
            return usuarioRepository.salvar(usuario);
        }
        catch (Exception e) {
            log.error("Erro ao tentar cadastrar o usuario: ", e.getMessage());
            throw new ErroAoCadastrarUsuario(e);
        }
    }
}
