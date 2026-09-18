package org.questao.estoque.estoque.movimentacao.service;

import org.questao.estoque.estoque.movimentacao.dominio.Movimentacao;
import org.questao.estoque.estoque.movimentacao.dto.MovimentacaoRequestDTO;
import org.questao.estoque.estoque.movimentacao.infraestrutura.MovimentacaoRepository;
import org.springframework.stereotype.Service;

@Service
public class VendaService {

    private final MovimentacaoRepository movimentacaoRepository;

    public VendaService(MovimentacaoRepository movimentacaoRepository) {
        this.movimentacaoRepository = movimentacaoRepository;
    }

    public Movimentacao venda(MovimentacaoRequestDTO movimentacaoRequestDTO) {
        Movimentacao movimentacao = Movimentacao.venda(movimentacaoRequestDTO.idPedido(),
                movimentacaoRequestDTO.quantidadeBotijaoCheio(),
                movimentacaoRequestDTO.quantidadeBotijaoVazio(),
                movimentacaoRequestDTO.quantidadeBotijaoCompleto(),
                movimentacaoRequestDTO.observacao(),
                movimentacaoRequestDTO.operacaoEntrega());
        return movimentacaoRepository.salvar(movimentacao);
    }

}
