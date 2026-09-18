package org.questao.notificacao.notificacao.solicitacao.infraestrutura.event;

import org.questao.notificacao.publicador.DomainEvent;

import java.time.Instant;

public record SolicitacaoEntregaAceitaEvent (
        Long idEntregador,
        Long idPedido,
        Instant ocorridoEm


) implements DomainEvent {
    public SolicitacaoEntregaAceitaEvent (Long idEntregador, Long idPedido) {
        this(idEntregador, idPedido, Instant.now());
    }
}
