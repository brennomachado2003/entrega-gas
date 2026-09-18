package org.questao.logistica.logistica.buscarLocalizacao.event;


import org.questao.logistica.publicador.DomainEvent;

import java.time.Instant;

public record EnderecoLocalizado(
        Long enderecoId,
        Double latitude,
        Double longitude,
        Instant ocorridoEm
)  implements DomainEvent {
    public EnderecoLocalizado(Long enderecoId, Double latitude, Double longitude) {
        this(enderecoId, latitude, longitude, Instant.now());
    }
}
