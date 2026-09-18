package org.questao.entregador.entregador.infraestrutura.kafka;

import org.questao.entregador.publicador.DomainEvent;

import java.time.Instant;

public record EntregadorCadastradoKafka(
        Long idEntregador,
        Instant ocorridoEm
) implements DomainEvent{
    public EntregadorCadastradoKafka(Long idEntregador) {
        this(idEntregador, Instant.now());
    }
}
