package org.questao.pedidos.publicador;

import java.time.Instant;

public interface DomainEvent {
    Instant ocorridoEm();
}
