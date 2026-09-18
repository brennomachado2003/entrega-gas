package org.questao.usuario.usuario.infraestrutura;

import org.questao.usuario.usuario.dominio.Usuario;

import java.util.List;
import java.util.stream.Collectors;

public interface UsuarioRepository {
    List<Usuario> listar();
    Usuario buscar(Long id);
    Usuario salvar(Usuario produto);
    Usuario atualizar(Long id, Usuario dados);
    void excluir(Long id);
    Usuario buscarPorEmail(String email);
}
