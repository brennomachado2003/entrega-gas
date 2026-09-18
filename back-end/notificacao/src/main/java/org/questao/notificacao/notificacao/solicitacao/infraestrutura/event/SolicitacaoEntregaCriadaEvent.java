package org.questao.notificacao.notificacao.solicitacao.infraestrutura.event;
import org.questao.notificacao.publicador.DomainEvent;

import java.time.Instant;

public record SolicitacaoEntregaCriadaEvent(
        Long idSolicitacao,
        Long idPedido,
        Long idEntregador,
        Instant ocorridoEm
) implements DomainEvent {

    public SolicitacaoEntregaCriadaEvent(
            Long idSolicitacao,
            Long idPedido,
            Long idEntregador
    ) {
        this( idSolicitacao,idPedido, idEntregador, Instant.now());
    }
}
