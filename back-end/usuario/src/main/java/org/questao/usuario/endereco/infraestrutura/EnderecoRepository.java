package org.questao.usuario.endereco.infraestrutura;

import org.questao.usuario.endereco.dominio.Endereco;
import org.questao.usuario.usuario.dominio.Usuario;

import java.util.List;

public interface EnderecoRepository {

    List<Endereco> listar();
    Endereco buscar(Long id);
    Endereco salvar(Endereco endereco);
    Endereco atualizar(Long id, Endereco dados);
    List<Endereco> listaEndereco(Usuario usuario);
}
