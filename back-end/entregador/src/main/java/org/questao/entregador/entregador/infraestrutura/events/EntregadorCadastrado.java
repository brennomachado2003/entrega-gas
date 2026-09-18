package org.questao.entregador.entregador.infraestrutura.events;

import org.questao.entregador.publicador.DomainEvent;

import java.time.Instant;

public record EntregadorCadastrado(
        Long idEntregador,
        Instant ocorridoEm
) implements DomainEvent{
    public EntregadorCadastrado(Long idEntregador) {
        this(idEntregador, Instant.now());
    }
}
