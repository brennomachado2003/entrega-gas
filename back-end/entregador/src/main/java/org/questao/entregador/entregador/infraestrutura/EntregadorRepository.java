package org.questao.entregador.entregador.infraestrutura;


import org.questao.entregador.entregador.dominio.Entregador;

import java.util.List;

public interface EntregadorRepository {

    List<Entregador> listar();

    Entregador buscar(Long id);

    Entregador salvar(Entregador entregador);

    Entregador atualizar(Long id, Entregador dados);

    void excluir(Long id);

    Entregador buscarPorCpf(String cpf);
}
