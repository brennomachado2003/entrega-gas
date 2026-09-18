package org.questao.estoque.estoque.movimentacao.service;

import org.questao.estoque.estoque.movimentacao.dominio.Movimentacao;
import org.questao.estoque.estoque.movimentacao.dto.MovimentacaoRequestDTO;
import org.questao.estoque.estoque.movimentacao.infraestrutura.MovimentacaoRepository;
import org.springframework.stereotype.Service;

@Service
public class ReabastecerService {

    private final MovimentacaoRepository movimentacaoRepository;

    public ReabastecerService(MovimentacaoRepository movimentacaoRepository) {
        this.movimentacaoRepository = movimentacaoRepository;
    }

    public Movimentacao reabastecer(MovimentacaoRequestDTO movimentacaoRequestDTO) {
        Movimentacao movimentacao = Movimentacao.reabastecer(movimentacaoRequestDTO.quantidadeBotijaoCheio(),
                movimentacaoRequestDTO.quantidadeBotijaoVazio(),
                movimentacaoRequestDTO.operacaoEntrega());
        return movimentacaoRepository.salvar(movimentacao);
    }
}
