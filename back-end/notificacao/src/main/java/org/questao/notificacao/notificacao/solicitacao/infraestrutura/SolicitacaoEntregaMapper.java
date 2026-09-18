package org.questao.notificacao.notificacao.solicitacao.infraestrutura;


import org.questao.notificacao.notificacao.solicitacao.dominio.SolicitacaoEntrega;
import org.questao.notificacao.notificacao.solicitacao.dto.SolicitacaoEntregaResponseDTO;

public final class SolicitacaoEntregaMapper {

    private SolicitacaoEntregaMapper() {
    }

    public static SolicitacaoEntregaEntity toEntity(SolicitacaoEntrega solicitacaoEntrega) {

        return new SolicitacaoEntregaEntity(
                solicitacaoEntrega.getIdSolicitacao(),
                solicitacaoEntrega.getPedido(),
                solicitacaoEntrega.getEntregador(),
                solicitacaoEntrega.getStatus(),
                solicitacaoEntrega.getDataSolicitacao()
        );
    }

    public static SolicitacaoEntrega toDomain(SolicitacaoEntregaEntity entity) {
        return SolicitacaoEntrega.reconstituir(
                entity.getIdSolicitacao(),
                entity.getPedido(),
                entity.getEntregador(),
                entity.getStatus(),
                entity.getDataSolicitacao()
        );
    }

    public static SolicitacaoEntregaResponseDTO solicitacaoEntregaResponseDTO(SolicitacaoEntrega solicitacaoEntrega) {
        return new SolicitacaoEntregaResponseDTO(solicitacaoEntrega.getIdSolicitacao(), solicitacaoEntrega.getEntregador(), solicitacaoEntrega.getStatus(), solicitacaoEntrega.getDataSolicitacao());
    }

}
