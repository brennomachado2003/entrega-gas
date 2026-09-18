package org.questao.logistica.logistica.consumer.events;

import org.questao.logistica.publicador.DomainEvent;

import java.time.Instant;
import java.util.List;

public record ListaEntregadoresProximosKafka (
        List<Long> lista,
        Long idPedido,
        Instant ocorridoEm
) implements DomainEvent {
    public ListaEntregadoresProximosKafka(List<Long> lista,  Long idPedido) {
        this(lista, idPedido,Instant.now());
    }
}
