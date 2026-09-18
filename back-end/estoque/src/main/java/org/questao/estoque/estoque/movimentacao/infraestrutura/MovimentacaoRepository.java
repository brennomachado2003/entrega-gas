package org.questao.estoque.estoque.movimentacao.infraestrutura;

import org.questao.estoque.estoque.movimentacao.dominio.Movimentacao;

import java.util.List;
import java.util.stream.Collectors;

public interface MovimentacaoRepository {

    List<Movimentacao> listar();

    Movimentacao buscar(Long id);

    Movimentacao salvar(Movimentacao movimentacao);

    Movimentacao atualizar(Long id, Movimentacao dados);
}
