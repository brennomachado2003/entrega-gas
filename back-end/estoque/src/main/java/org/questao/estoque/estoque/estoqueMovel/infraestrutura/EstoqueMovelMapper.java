package org.questao.estoque.estoque.estoqueMovel.infraestrutura;

import org.questao.estoque.estoque.movimentacao.dominio.Movimentacao;
import org.questao.estoque.estoque.movimentacao.infraestrutura.MovimentacaoMapper;
import org.questao.estoque.estoque.estoqueMovel.dominio.EstoqueMovel;
import org.questao.estoque.estoque.estoqueMovel.dto.EstoqueMovelRequestDTO;
import org.questao.estoque.estoque.estoqueMovel.dto.EstoqueMovelResponseDTO;

import java.util.List;

public final class EstoqueMovelMapper {

    private EstoqueMovelMapper() {
    }

    public static EstoqueMovelEntity toEntity(EstoqueMovel estoqueMovel) {
        List<Movimentacao> movimentacaos = estoqueMovel.getMovimentacoes();

        return new EstoqueMovelEntity(
                estoqueMovel.getIdOperacaoEntrega(),
                estoqueMovel.getEntregador(),
                estoqueMovel.getDataHora(),
                movimentacaos.stream().map(MovimentacaoMapper::toEntity).toList()
        );
    }

    public static EstoqueMovel toDomain(EstoqueMovelEntity entity) {
        return EstoqueMovel.reconstituir(
                entity.getIdOperacaoEntrega(),
                entity.getEntregador(),
                entity.getDataHora(),
                entity.getMovimentacoes().stream().map(MovimentacaoMapper::toDomain).toList()
        );
    }

    public static EstoqueMovelResponseDTO movimentcaoResponseDTO(EstoqueMovel estoqueMovel) {
        return new EstoqueMovelResponseDTO(
                estoqueMovel.getIdOperacaoEntrega(),
                estoqueMovel.getEntregador(),
                estoqueMovel.getDataHora()
        );
    }

    public static EstoqueMovel estoqueMovelRequestDTO(EstoqueMovelRequestDTO estoqueMovelRequestDTO) {
        return EstoqueMovel.criar(estoqueMovelRequestDTO.entregador());
    }

}
