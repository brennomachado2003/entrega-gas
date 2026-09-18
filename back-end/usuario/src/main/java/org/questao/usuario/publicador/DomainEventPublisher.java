package org.questao.usuario.publicador;

import java.util.Collection;

public interface DomainEventPublisher {
    void publicar(Collection<DomainEvent> events);
}
