package org.questao.pedidos.pedido.intraestrutura.kafka;

import org.questao.pedidos.publicador.DomainEvent;

import java.time.Instant;

public record PedidoCriadoKafka(
        Long idPedido,
        Long idEndereco,
        Instant ocorridoEm
) implements DomainEvent {
    public PedidoCriadoKafka(Long idPedido, Long idEndereco) {
        this(idPedido, idEndereco, Instant.now());
    }
}
