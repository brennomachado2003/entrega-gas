package org.questao.estoque.estoque.movimentacao.service;

import org.questao.estoque.estoque.movimentacao.dominio.Movimentacao;
import org.questao.estoque.estoque.movimentacao.dto.MovimentacaoRequestDTO;
import org.questao.estoque.estoque.movimentacao.infraestrutura.MovimentacaoRepository;
import org.springframework.stereotype.Service;

@Service
public class CargaInicialService {

    private final MovimentacaoRepository movimentacaoRepository;

    public CargaInicialService(MovimentacaoRepository movimentacaoRepository) {
        this.movimentacaoRepository = movimentacaoRepository;
    }

    public Movimentacao CargaInicial(MovimentacaoRequestDTO movimentacaoRequestDTO) {
        Movimentacao movimentacao = Movimentacao.cargaInicial(movimentacaoRequestDTO.quantidadeBotijaoCheio(), movimentacaoRequestDTO.observacao(), movimentacaoRequestDTO.operacaoEntrega());
        return movimentacaoRepository.salvar(movimentacao);
    }
}
