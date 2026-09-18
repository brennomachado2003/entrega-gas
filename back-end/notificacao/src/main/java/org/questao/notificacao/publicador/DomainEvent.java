package org.questao.notificacao.publicador;

import java.time.Instant;

public interface DomainEvent {
    Instant ocorridoEm();
}
