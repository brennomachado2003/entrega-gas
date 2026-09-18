package org.questao.pedidos.pedido.intraestrutura.events;

import org.questao.pedidos.publicador.DomainEvent;

import java.time.Instant;

public record SolicitacaoEntregaAceitaEvent(
        Long idEntregador,
        Long idPedido,
        Instant ocorridoEm


) implements DomainEvent {
    public SolicitacaoEntregaAceitaEvent(Long idEntregador, Long idPedido) {
        this(idEntregador, idPedido, Instant.now());
    }
}
