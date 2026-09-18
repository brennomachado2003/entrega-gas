package org.questao.estoque.estoque.movimentacao.infraestrutura;

import org.questao.estoque.estoque.movimentacao.dominio.Movimentacao;
import org.questao.estoque.estoque.movimentacao.dto.MovimentacaoRequestDTO;
import org.questao.estoque.estoque.movimentacao.dto.MovimentcaoResponseDTO;

public final class MovimentacaoMapper {

    private MovimentacaoMapper() {
    }

    public static MovimentacaoEntity toEntity(Movimentacao movimentacao) {
        return new MovimentacaoEntity(
                movimentacao.getIdMovimentacao(),
                movimentacao.getPedido(),
                movimentacao.getTipoMovimentacao(),
                movimentacao.getDataHora(),
                movimentacao.getQuantidadeBotijaoCheio(),
                movimentacao.getQuantidadeBotijaoVazio(),
                movimentacao.getQuantidadeBotijaoCompleto(),
                movimentacao.getObservacao(),
                movimentacao.getOperacaoEntrega()

        );
    }

    public static Movimentacao toDomain(MovimentacaoEntity entity) {
        return Movimentacao.reconstituir(
                entity.getIdMovimentacao(),
                entity.getPedido(),
                entity.getTipoMovimentacao(),
                entity.getDataHora(),
                entity.getQuantidadeBotijaoCheio(),
                entity.getQuantidadeBotijaoVazio(),
                entity.getQuantidadeBotijaoCompleto(),
                entity.getObservacao(),
                entity.getOperacaoEntrega()
        );
    }

    public static MovimentcaoResponseDTO movimentcaoResponseDTO(Movimentacao movimentacao) {
        return new MovimentcaoResponseDTO(
                movimentacao.getOperacaoEntrega(),
                movimentacao.getPedido(),
                movimentacao.getDataHora(),
                movimentacao.getQuantidadeBotijaoCheio(),
                movimentacao.getQuantidadeBotijaoVazio(),
                movimentacao.getQuantidadeBotijaoCompleto(),
                movimentacao.getTipoMovimentacao().name()
        );
    }

    public static Movimentacao movimentacaoRequestDTO(MovimentacaoRequestDTO movimentacaoRequestDTO) {
        return Movimentacao.reconstituir(
                null,
                movimentacaoRequestDTO.idPedido(),
                movimentacaoRequestDTO.tipoMovimentacao(),
                movimentacaoRequestDTO.dataEntrega(),
                movimentacaoRequestDTO.quantidadeBotijaoCheio(),
                movimentacaoRequestDTO.quantidadeBotijaoVazio(),
                movimentacaoRequestDTO.quantidadeBotijaoCompleto(),
                movimentacaoRequestDTO.observacao(),
                movimentacaoRequestDTO.operacaoEntrega()
        );
    }


}
