package org.questao.usuario.usuario.infraestrutura;

import org.questao.usuario.usuario.dominio.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsuarioRepositoryAdapter implements UsuarioRepository {

    private final UsuarioJPARepository usuarioJPARepository;

    public UsuarioRepositoryAdapter(UsuarioJPARepository usuarioJPARepository) {
        this.usuarioJPARepository = usuarioJPARepository;
    }

    @Override
    public List<Usuario> listar() {
        List<UsuarioEntity> produtos = usuarioJPARepository.findAll();
        return produtos.stream().map(UsuarioMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public Usuario buscar(Long id) {
        UsuarioEntity usuario = usuarioJPARepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Usuario não encontrado: " + id));
        return UsuarioMapper.toDomain(usuario);
    }

    @Override
    public Usuario buscarPorEmail(String email) {
        UsuarioEntity usuario = usuarioJPARepository.findByEmail(email);
        return UsuarioMapper.toDomain(usuario);
    }

    @Override
    public Usuario salvar(Usuario usuario) {
        UsuarioEntity usuarioEntity = UsuarioMapper.toEntity(usuario);
        return UsuarioMapper.toDomain(usuarioJPARepository.save(usuarioEntity));
    }

    @Override
    public Usuario atualizar(Long id, Usuario dados) {
        Usuario usuario = buscar(id);
        usuario.atualizar(dados);
        return salvar(usuario);
    }

    @Override
    public void excluir(Long id) {
        Usuario produto = buscar(id);
        usuarioJPARepository.delete(UsuarioMapper.toEntity(produto));
    }
}
