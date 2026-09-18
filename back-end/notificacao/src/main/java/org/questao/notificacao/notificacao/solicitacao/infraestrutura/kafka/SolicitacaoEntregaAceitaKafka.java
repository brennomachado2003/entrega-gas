package org.questao.notificacao.notificacao.solicitacao.infraestrutura.kafka;

import org.questao.notificacao.publicador.DomainEvent;

import java.time.Instant;

public record SolicitacaoEntregaAceitaKafka(
        Long idEntregador,
        Long idPedido,
        Instant ocorridoEm


) implements DomainEvent {
    public SolicitacaoEntregaAceitaKafka(Long idEntregador, Long idPedido) {
        this(idEntregador, idPedido, Instant.now());
    }
}
