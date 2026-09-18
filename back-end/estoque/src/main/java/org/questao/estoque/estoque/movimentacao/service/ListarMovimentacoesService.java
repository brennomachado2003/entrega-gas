package org.questao.estoque.estoque.movimentacao.service;

import org.questao.estoque.estoque.movimentacao.dominio.Movimentacao;
import org.questao.estoque.estoque.movimentacao.infraestrutura.MovimentacaoRepository;

import java.util.List;

public class ListarMovimentacoesService {

    private final MovimentacaoRepository movimentacaoRepository;

    public ListarMovimentacoesService(MovimentacaoRepository movimentacaoRepository) {
        this.movimentacaoRepository = movimentacaoRepository;
    }

    public List<Movimentacao> listarMovimentacoes() {
        return movimentacaoRepository.listar();
    }
}
