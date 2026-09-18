package org.questao.usuario.usuario.service;

import org.questao.usuario.usuario.dominio.Senha;
import org.questao.usuario.usuario.dominio.Usuario;
import org.questao.usuario.usuario.infraestrutura.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CadastrarUsuarioService {

    private UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    public CadastrarUsuarioService(UsuarioRepository usuarioRepository, BCryptPasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario cadastrar(Usuario usuario) {
        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha().senha());
        usuario.setSenha(new Senha(senhaCriptografada));
        return usuarioRepository.salvar(usuario);
    }
}
